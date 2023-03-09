package com.dashboardk.domain

import com.dashboardk.di.inject
import com.dashboardk.domain.configs.ConfigFetcher

/**
 * Root model for graphql.
 * Will be consumed by GraphQL to generate GraphQL Endpoint
 */
@Suppress("unused")
class DashboardSystem {
    private val configFetcher by lazy { inject<ConfigFetcher>() }
    fun crawlData() {

    }
}