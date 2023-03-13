package com.dashboardk.graphql.nodes

import com.dashboardk.domain.collectors.CollectorService

/**
 * Root model for graphql.
 * Will be consumed by GraphQL to generate GraphQL Endpoint
 */
class EntryNode {

    private val collectorService: CollectorService by lazy { CollectorService() }

    fun collectData(): Boolean {
        collectorService.collectData()
        return true
    }
}
