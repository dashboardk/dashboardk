package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.Collector
import com.dashboardk.backend.domain.collectors.infos.RepoInfo
import com.dashboardk.backend.dtos.GitHubRepoInfoDto
import com.dashboardk.backend.repositories.RepoInfoRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

abstract class RepoCollector: Collector() {

    private val repoInfoRepository by lazy { inject<RepoInfoRepository>() }

    @OptIn(FlowPreview::class)
    override fun collectData(): Flow<Unit> {
        return collectRepoInfo().flatMapMerge {
            storeRepoInfo(it.fullName)
        }
    }

    abstract fun collectRepoInfo(): Flow<RepoInfo>

    private fun storeRepoInfo(name: String): Flow<Unit> {
        return repoInfoRepository.storeRepo(name)
    }
}