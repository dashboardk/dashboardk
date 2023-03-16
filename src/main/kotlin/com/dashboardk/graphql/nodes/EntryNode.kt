package com.dashboardk.graphql.nodes

import com.dashboardk.domain.collectors.CollectorService
import kotlinx.coroutines.flow.collect

/**
 * Root model for graphql.
 * Will be consumed by GraphQL to generate GraphQL Endpoint
 */
class EntryNode {

    private val collectorService: CollectorService by lazy { CollectorService() }

    suspend fun collectData(): Boolean {
        collectorService.collectData().collect()
        return true
    }
}
