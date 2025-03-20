package com.github.reposview.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.reposview.domain.model.OwnerModel
import com.github.reposview.domain.model.RepositoryModel
import com.github.reposview.domain.usecase.GetListRepositoriesUseCase
import com.github.reposview.presentation.state.RepositoriesViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListRepositoriesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getListRepositoriesUseCase: GetListRepositoriesUseCase

    private lateinit var viewModel: ListRepositoriesViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ListRepositoriesViewModel(getListRepositoriesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getRepositories should emit Loading state initially`() = runTest {
        `when`(getListRepositoriesUseCase()).thenReturn(Result.success(emptyList()))

        val stateList = mutableListOf<RepositoriesViewState>()
        viewModel.viewState.observeForever { stateList.add(it) }

        viewModel.getRepositories()

        advanceUntilIdle()

        assert(stateList.contains(RepositoriesViewState.Loading))

        viewModel.viewState.removeObserver { stateList.add(it) }
    }

    @Test
    fun `getRepositories should emit Success state when use case returns success`() = runTest {
        val repositories = listOf(
            RepositoryModel(
                id = 1,
                nodeId = "node1",
                name = "Repo1",
                fullName = "User/Repo1",
                owner = OwnerModel("User", 1, "avatar_url"),
                isPrivate = false,
                htmlUrl = "https://github.com/User/Repo1",
                description = "Description 1",
                isFork = false,
                language = "Kotlin"
            ),
            RepositoryModel(
                id = 2,
                nodeId = "node2",
                name = "Repo2",
                fullName = "User/Repo2",
                owner = OwnerModel("User", 2, "avatar_url"),
                isPrivate = false,
                htmlUrl = "https://github.com/User/Repo2",
                description = "Description 2",
                isFork = false,
                language = "Java"
            )
        )
        `when`(getListRepositoriesUseCase()).thenReturn(Result.success(repositories))

        viewModel.getRepositories()

        assertEquals(RepositoriesViewState.Success(repositories), viewModel.viewState.value)
    }

    @Test
    fun `getRepositories should emit Error state when use case returns failure`() = runTest {
        val exception = Exception("Erro ao buscar repositórios")
        `when`(getListRepositoriesUseCase()).thenReturn(Result.failure(exception))

        viewModel.getRepositories()

        assertEquals(RepositoriesViewState.Error("Erro ao buscar repositórios"), viewModel.viewState.value)
    }

    @Test
    fun `getRepositories should emit Error state when an unexpected exception occurs`() = runTest {
        val exception = RuntimeException("Erro inesperado")
        `when`(getListRepositoriesUseCase()).thenThrow(exception)

        viewModel.getRepositories()

        assertEquals(RepositoriesViewState.Error("Erro inesperado"), viewModel.viewState.value)
    }
}
