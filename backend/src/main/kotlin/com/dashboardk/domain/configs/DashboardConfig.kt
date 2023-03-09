package com.dashboardk.domain.configs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardConfig(
    val repos: List<CodeRepoConfig>?
)

@Serializable
data class CodeRepoConfig(
    val name: String,
    val provider: RepoProvider,
    val url: String,
    val token: String
)

@Serializable
enum class RepoProvider {
    @SerialName("github")
    GITHUB
}