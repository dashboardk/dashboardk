package com.dashboardk.domain.collectors

import com.dashboardk.di.inject
import com.dashboardk.domain.meta.MetaInfo
import com.dashboardk.domain.meta.MetaInfoService
import com.dashboardk.domain.meta.RepoProvider.*
import kotlinx.coroutines.flow.*

class CollectorService {
    private val metaInfoService by lazy { inject<MetaInfoService>() }
    fun collectData(): Flow<Unit> {
        return flow {
            val configMetaData = metaInfoService.getDashboardMetaInfo()
            getCollectors(configMetaData).map {
                it.collect().collect()
            }
        }
    }

    private fun getCollectors(metaInfo: MetaInfo): List<Collector<*>> {
        val collectors = mutableListOf<Collector<*>>()

        metaInfo.repos?.forEach {
            when(it.provider) {
                GITHUB -> collectors.add(GitHubRepoCollector(repoName = it.name, token = it.token, repoPath = it.path))
            }
        }

        return collectors
    }
}