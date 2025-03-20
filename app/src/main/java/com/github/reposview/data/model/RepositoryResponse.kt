package com.github.reposview.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: OwnerResponse,
    @SerializedName("private") val private: Boolean,
    @SerializedName("htmlUrl") val htmlUrl: String,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("language") val language: String?
)
