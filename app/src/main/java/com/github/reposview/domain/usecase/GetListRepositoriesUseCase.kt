package com.github.reposview.domain.usecase

import com.github.reposview.domain.model.RepositoryModel
import com.github.reposview.domain.repository.GetListRepositoriesRepository

class GetListRepositoriesUseCase(
    private val getListRepositoriesRepository: GetListRepositoriesRepository
) {
    suspend operator fun invoke(): Result<List<RepositoryModel>> {
        return getListRepositoriesRepository.getRepos()
    }
}
