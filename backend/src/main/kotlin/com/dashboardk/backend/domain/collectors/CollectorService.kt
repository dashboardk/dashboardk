package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.domain.meta.MetaInfo
import com.dashboardk.backend.domain.meta.MetaInfoService
import com.dashboardk.backend.domain.meta.RepoProvider.GITHUB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CollectorService(private val metaInfoService: MetaInfoService) {
    fun collectDataFlow(): Flow<Unit> {
        return flow {
            val configMetaData = metaInfoService.getDashboardMetaInfo()
            getCollectors(configMetaData).map {
                it.collectData().collect()
            }
        }
    }

    private fun getCollectors(metaInfo: MetaInfo): List<Collector> {
        val collectors = mutableListOf<Collector>()

        metaInfo.repos?.forEach {
            when (it.provider) {
                GITHUB -> {
                    collectors.add(GitHubRepoCollector(repoPath = it.name, token = it.token))
                    collectors.add(GitHubCommitCollector(repoPath = it.name, token = it.token))
                }
            }
        }

        return collectors
    }
}