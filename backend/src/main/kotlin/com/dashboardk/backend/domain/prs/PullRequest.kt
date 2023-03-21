package com.dashboardk.backend.domain.prs

import com.dashboardk.backend.domain.collaborator.Collaborator

class PullRequest(
    val id: Long,
    val title: String,
    val state: PRState,
    val openedBy: Collaborator
)