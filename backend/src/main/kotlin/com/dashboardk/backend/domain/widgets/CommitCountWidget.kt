package com.dashboardk.backend.domain.widgets

import com.dashboardk.backend.di.inject
import com.dashboardk.backend.repositories.CommitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("CommitCount")
class CommitCountWidget(
    override val name: String,
    override val type: String,
    val repoName: String
) : Widget() {

    private val commitRepository by lazy { inject<CommitRepository>() }

    override fun getData(): Flow<Long> {
        return commitRepository.getCommitCount(repoName)
    }
}