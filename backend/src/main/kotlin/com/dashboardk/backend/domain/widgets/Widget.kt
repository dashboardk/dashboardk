package com.dashboardk.backend.domain.widgets

import kotlinx.serialization.Serializable

@Serializable
class Widget(
    val name: String,
    val type: String,
    val branchName: String?,
    val repoName: String?
)