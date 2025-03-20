package com.github.reposview.domain.repository

import com.github.reposview.domain.model.RepositoryModel

interface GetListRepositoriesRepository {
    suspend fun getRepos(): Result<List<RepositoryModel>>
}
