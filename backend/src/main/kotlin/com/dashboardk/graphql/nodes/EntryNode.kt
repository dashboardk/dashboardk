package com.dashboardk.graphql.nodes

import com.expediagroup.graphql.annotations.GraphQLContext

class EntryNode {
    fun dashboard(): DashboardNode {
        return DashboardNode("")
    }

}
