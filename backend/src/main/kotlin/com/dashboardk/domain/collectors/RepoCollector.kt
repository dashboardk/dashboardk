package com.dashboardk.domain.collectors

import com.dashboardk.di.inject
import com.dashboardk.dtos.GitHubRepoInfoDto
import com.dashboardk.repositories.RepoInfoRepository
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

    abstract fun collectRepoInfo(): Flow<GitHubRepoInfoDto>

    private fun storeRepoInfo(name: String): Flow<Unit> {
        return repoInfoRepository.storeRepo(name)
    }
}