package com.github.reposview.presentation.state

import com.github.reposview.domain.model.RepositoryModel

sealed class RepositoriesViewState {
    object Loading : RepositoriesViewState()
    data class Success(val repositories: List<RepositoryModel>) : RepositoriesViewState()
    data class Error(val message: String) : RepositoriesViewState()
}
