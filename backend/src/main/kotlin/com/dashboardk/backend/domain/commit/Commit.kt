package com.dashboardk.backend.domain.commit

class Commit(
    val id: Long,
    val sha: String,
    val message: String,
    val repoId: Long
) {
}