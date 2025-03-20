package com.github.reposview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.reposview.domain.usecase.GetListRepositoriesUseCase
import com.github.reposview.presentation.state.RepositoriesViewState
import kotlinx.coroutines.launch

class ListRepositoriesViewModel(
    private val getListRepositoriesUseCase: GetListRepositoriesUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<RepositoriesViewState>()
    val viewState: LiveData<RepositoriesViewState> get() = _viewState

    fun getRepositories() {
        viewModelScope.launch {
            _viewState.value = RepositoriesViewState.Loading
            try {
                val result = getListRepositoriesUseCase()
                result.onSuccess { repos ->
                    _viewState.value = RepositoriesViewState.Success(repos)
                }.onFailure { exception ->
                    _viewState.value = RepositoriesViewState.Error(exception.message ?: "Erro desconhecido")
                }
            } catch (e: Exception) {
                _viewState.value = RepositoriesViewState.Error(e.message ?: "Erro inesperado")
            }
        }
    }
}
