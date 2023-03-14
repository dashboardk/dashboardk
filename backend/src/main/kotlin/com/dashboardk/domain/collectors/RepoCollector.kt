package com.dashboardk.domain.collectors

import com.dashboardk.di.inject
import com.dashboardk.domain.repo.Repo
import com.dashboardk.dtos.RepoInfoDto
import com.dashboardk.repositories.RepoInfoRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

abstract class RepoCollector: Collector<Repo>() {

    private val repoInfoRepository by lazy { inject<RepoInfoRepository>() }

    @OptIn(FlowPreview::class)
    override fun collect(): Flow<Repo> {
        return collectData().flatMapMerge {
            storeRepoInfo(it.name)
        }
    }

    abstract fun collectData(): Flow<RepoInfoDto>

    private fun storeRepoInfo(name: String): Flow<Repo> {
        return repoInfoRepository.storeRepo(name)
    }
}