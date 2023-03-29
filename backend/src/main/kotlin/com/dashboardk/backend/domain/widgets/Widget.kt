package com.dashboardk.backend.domain.widgets

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
sealed class Widget {
    abstract val name: String
    abstract val type: String

    abstract fun getData(): Flow<*>
}