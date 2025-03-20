package com.github.reposview.data.model

data class RepositoryResponse(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val owner: OwnerResponse,
    val private: Boolean,
    val html_url: String,
    val description: String?,
    val fork: Boolean,
    val language: String?
)
