package com.dashboardk.domain.collectors

import kotlinx.coroutines.flow.Flow

abstract class Collector {
    abstract fun collectData(): Flow<Unit>
}