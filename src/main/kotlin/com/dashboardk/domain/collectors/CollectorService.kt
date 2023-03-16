package com.dashboardk.domain.collectors

import com.dashboardk.di.inject
import com.dashboardk.domain.meta.MetaInfo
import com.dashboardk.domain.meta.MetaInfoService
import com.dashboardk.domain.meta.RepoProvider.GITHUB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CollectorService {
    private val metaInfoService by lazy { inject<MetaInfoService>() }
    fun collectData(): Flow<Unit> {
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