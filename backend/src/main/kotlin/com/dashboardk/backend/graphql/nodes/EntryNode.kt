package com.dashboardk.backend.graphql.nodes

import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.CollectorService
import com.dashboardk.backend.domain.meta.MetaInfoService
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

/**
 * Root model for graphql.
 * Will be consumed by GraphQL to generate GraphQL Endpoint
 */
class EntryNode {

    private val collectorService: CollectorService by lazy { inject() }
    private val metaInfoService: MetaInfoService by lazy { inject() }

    suspend fun collectData(): Boolean {
        collectorService.collectDataFlow().collect()
        return true
    }

    fun metaInfo(): MetaInfoNode {
        return MetaInfoNode(metaInfoService.getDashboardMetaInfo())
    }

    suspend fun widgetData(widgetName: String): String? {
        return Gson().toJson(
            metaInfoService.getDashboardMetaInfo().widgets.find { it.name == widgetName }?.getData()?.first()
        )
    }
}
