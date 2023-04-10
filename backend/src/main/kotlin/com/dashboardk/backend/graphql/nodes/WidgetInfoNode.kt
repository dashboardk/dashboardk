package com.dashboardk.backend.graphql.nodes

import com.dashboardk.backend.domain.widgets.types.Widget
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

class WidgetInfoNode(
    val name: String,
    val type: String,
    private val widget: Widget
) {
    constructor(widget: Widget) : this(widget.name, widget.type, widget)

    suspend fun data(): String {
        return Gson().toJson(widget.getData().first())
    }
}