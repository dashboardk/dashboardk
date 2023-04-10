package com.dashboardk.backend.domain.widgets.datas

data class CommitCountWidgetData(
    val noOfCommits: Int,
    val commits: List<CommitInfoData>
)