package com.dashboardk.backend.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepoInfoDto(
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName:String,
    @SerialName("description") val description: String,
    @SerialName("default_branch") val defaultBranch: String
)