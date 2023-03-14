package com.dashboardk.domain.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaInfo(
    val repos: List<RepoMetaInfo>?
)

@Serializable
data class RepoMetaInfo(
    val name: String,
    val provider: RepoProvider,
    val path: String,
    val token: String
)

@Serializable
enum class RepoProvider {
    @SerialName("github")
    GITHUB
}