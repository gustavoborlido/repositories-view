package com.github.reposview.data.mapper

import com.github.reposview.data.model.OwnerResponse
import com.github.reposview.data.model.RepositoryResponse
import com.github.reposview.domain.model.OwnerModel
import com.github.reposview.domain.model.RepositoryModel

fun RepositoryResponse.toDomain(): RepositoryModel {
    return RepositoryModel(
        id = this.id,
        nodeId = this.nodeId,
        name = this.name,
        fullName = this.fullName,
        owner = this.owner.toDomain(),
        isPrivate = this.private,
        htmlUrl = this.htmlUrl,
        description = this.description,
        isFork = this.fork,
        language = this.language
    )
}

fun OwnerResponse.toDomain(): OwnerModel {
    return OwnerModel(
        login = this.login,
        id = this.id,
        avatarUrl = this.avatarUrl
    )
}
