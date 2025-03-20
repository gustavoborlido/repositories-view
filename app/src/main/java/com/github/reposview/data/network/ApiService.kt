package com.github.reposview.data.network

import com.github.reposview.data.model.RepositoryResponse
import retrofit2.http.GET

interface ApiService {
    @GET("repositories")
    suspend fun getRepos(): List<RepositoryResponse>
}
