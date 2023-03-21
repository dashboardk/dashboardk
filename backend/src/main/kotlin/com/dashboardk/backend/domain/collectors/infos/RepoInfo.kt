package com.dashboardk.backend.domain.collectors.infos

data class RepoInfo(
    val name: String,
    val fullName:String,
    val description: String,
    val defaultBranch: String
)