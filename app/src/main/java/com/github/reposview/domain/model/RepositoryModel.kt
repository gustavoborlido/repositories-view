package com.github.reposview.domain.model

data class RepositoryModel(
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val owner: OwnerModel,
    val isPrivate: Boolean,
    val htmlUrl: String,
    val description: String?,
    val isFork: Boolean,
    val language: String?
)
