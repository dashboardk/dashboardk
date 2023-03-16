package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.Collector
import com.dashboardk.backend.dtos.GithubCommitInfoDto
import com.dashboardk.backend.repositories.CommitRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

abstract class CommitCollector : Collector() {

    private val commitRepository by lazy { inject<CommitRepository>() }

    @OptIn(FlowPreview::class)
    override fun collectData(): Flow<Unit> {
        return getRepoId().flatMapMerge { repoId ->
            collectCommitInfo().flatMapMerge { commits ->
                commitRepository.storeCommits(repoId = repoId, commitInfos = commits)
            }
        }
    }

    abstract fun getRepoId(): Flow<Long>

    abstract fun collectCommitInfo(): Flow<List<GithubCommitInfoDto>>
}