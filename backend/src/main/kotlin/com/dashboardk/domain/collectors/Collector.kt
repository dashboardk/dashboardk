package com.dashboardk.domain.collectors

import kotlinx.coroutines.flow.Flow

abstract class Collector<T> {
    abstract fun collect(): Flow<T>
}