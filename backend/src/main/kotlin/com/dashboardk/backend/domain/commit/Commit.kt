package com.dashboardk.backend.domain.commit

import com.dashboardk.backend.domain.collaborator.Collaborator

class Commit(
    val id: Long,
    val sha: String,
    val message: String,
    val collaborator: Collaborator
)