package com.dashboardk.graphql

import com.dashboardk.graphql.nodes.EntryNode
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import graphql.ExecutionInput
import graphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

data class GraphQLRequest(val query: String?, val operationName: String?, val variables: Map<String, Any>?)

fun Application.configureGraphQL() {

    val config = SchemaGeneratorConfig(listOf("com.dashboardk.graphql.nodes"))
    val queries = listOf(TopLevelObject(EntryNode()))
    val graphQL = GraphQL.newGraphQL(toSchema(config = config, queries = queries, mutations = queries)).build()

    suspend fun ApplicationCall.executeQuery() {
        val request = receive<GraphQLRequest>()
        val executionInput = ExecutionInput.newExecutionInput()
            .query(request.query)
            .operationName(request.operationName)
            .variables(request.variables)
            .build()

        respond(graphQL.execute(executionInput))
    }

    routing {
        post("/graphql") {
            call.executeQuery()
        }

        static("/playground") {
            defaultResource("static/graphql/playground.html")
        }
    }
}
