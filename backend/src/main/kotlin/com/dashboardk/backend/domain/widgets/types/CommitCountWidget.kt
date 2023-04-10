package com.dashboardk.backend.domain.widgets.types

import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.widgets.datas.CommitCountFrom
import com.dashboardk.backend.domain.widgets.datas.CommitCountFrom.EVER
import com.dashboardk.backend.domain.widgets.datas.CommitCountFrom.TODAY
import com.dashboardk.backend.domain.widgets.datas.CommitCountWidgetData
import com.dashboardk.backend.domain.widgets.datas.CommitInfoData
import com.dashboardk.backend.repositories.CommitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@SerialName("CommitCount")
class CommitCountWidget(
    override val name: String,
    override val type: String,
    val repoName: String,
    val from: CommitCountFrom
) : Widget() {

    private val commitRepository by lazy { inject<CommitRepository>() }
    override fun getData(): Flow<CommitCountWidgetData> {
        val fromTime = getFromTime(from)
        return combine(
            commitRepository.getCommitCount(repoName, fromTime),
            commitRepository.getCommits(repoName, fromTime)
        ) { commitCount, commits ->
            CommitCountWidgetData(noOfCommits = commitCount.toInt(), commits = commits.map {
                CommitInfoData(it.message, it.collaborator.name)
            })
        }
    }

    private fun getFromTime(from: CommitCountFrom): LocalDateTime {
        return when (from) {
            TODAY -> LocalDateTime.now().minusDays(1)
            EVER -> LocalDateTime.now().minusYears(10)
        }
    }
}
