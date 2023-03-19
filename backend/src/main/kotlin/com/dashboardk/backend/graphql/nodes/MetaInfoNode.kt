package com.dashboardk.backend.graphql.nodes

import com.dashboardk.backend.domain.meta.MetaInfo

class MetaInfoNode(val widgets: List<WidgetInfoNode>) {
    constructor(metaInfo: MetaInfo) : this(widgets = metaInfo.widgets.map { widget -> WidgetInfoNode(widget) })
}