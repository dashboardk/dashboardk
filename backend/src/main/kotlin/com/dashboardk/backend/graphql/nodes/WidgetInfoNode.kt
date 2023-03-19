package com.dashboardk.backend.graphql.nodes

import com.dashboardk.backend.domain.widgets.Widget

class WidgetInfoNode(
    val name: String,
    val type: String,
    val repoName: String?,
    val branchName: String?
) {
    constructor(widget: Widget) : this(widget.name, widget.type, widget.repoName, widget.branchName)
}