package com.dashboardk.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubCommitInfoDto(
    @SerialName("sha") val sha: String,
    @SerialName("commit") val commit: GithubCommitDto
)

@Serializable
data class GithubCommitDto(
    @SerialName("message") val message: String
)