package com.dashboardk.backend.graphql

import com.dashboardk.backend.graphql.nodes.EntryNode
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import graphql.ExecutionInput
import graphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

data class GraphQLRequest(val query: String?, val operationName: String?, val variables: Map<String, Any>?)

suspend fun ApplicationCall.executeQuery() {
    val config = SchemaGeneratorConfig(listOf("com.dashboardk.backend.graphql.nodes"))
    val queries = listOf(TopLevelObject(EntryNode()))
    val graphQL = GraphQL.newGraphQL(toSchema(config = config, queries = queries, mutations = queries)).build()

    val request = receive<GraphQLRequest>()
    val executionInput = ExecutionInput.newExecutionInput()
        .query(request.query)
        .operationName(request.operationName)
        .variables(request.variables)
        .build()

    respond(graphQL.execute(executionInput))
}
