package com.github.reposview.data.repository

import com.github.reposview.data.mapper.toDomain
import com.github.reposview.data.network.ApiService
import com.github.reposview.domain.model.RepositoryModel
import com.github.reposview.domain.repository.GetListRepositoriesRepository

class GetListRepositoriesImpl(val apiService: ApiService): GetListRepositoriesRepository {
    override  suspend fun getRepos(): Result<List<RepositoryModel>> {
        return runCatching {
            apiService.getRepos().map { it.toDomain() }
        }
    }
}
