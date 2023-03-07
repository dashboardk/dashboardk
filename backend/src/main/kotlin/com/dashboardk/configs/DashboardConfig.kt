package com.dashboardk.configs

data class DashboardConfig(
    val codeRepoConfigs: List<CodeRepoConfig>?
)

data class CodeRepoConfig(
    val repoToolType: RepoToolType,
    val repoUrl: String,
    val token: String
)

enum class RepoToolType {
    GITHUB
}