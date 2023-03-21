package com.dashboardk.backend.domain.collectors.infos

import java.time.Instant

data class CommitInfo(
    val sha: String,
    val message: String,
    val time: Instant
)