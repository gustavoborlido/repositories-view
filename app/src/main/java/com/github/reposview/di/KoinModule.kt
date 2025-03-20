package com.github.reposview.di

import com.github.reposview.data.network.ApiService
import com.github.reposview.data.network.RetrofitClient
import com.github.reposview.data.repository.GetListRepositoriesImpl
import com.github.reposview.domain.repository.GetListRepositoriesRepository
import com.github.reposview.domain.usecase.GetListRepositoriesUseCase
import com.github.reposview.presentation.viewmodel.ListRepositoriesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiService> { RetrofitClient.create() }
    single<GetListRepositoriesUseCase> {GetListRepositoriesUseCase(get())}
    single<GetListRepositoriesRepository> {GetListRepositoriesImpl(get())}

    viewModel { ListRepositoriesViewModel(get()) }
}
