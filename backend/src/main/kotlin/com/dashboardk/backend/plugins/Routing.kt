package com.dashboardk.backend.plugins

import com.dashboardk.backend.graphql.executeQuery
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

private val BASE_RESOURCES = listOf(
    "favicon.ico",
    "manifest.json",
    "service-worker.js"
)

private const val RESOURCE_DIRECTORY = "client"
private const val RESOURCE_STATIC_DIRECTORY = "$RESOURCE_DIRECTORY/static"
private const val RESOURCE_INDEX = "$RESOURCE_DIRECTORY/index.html"
fun Application.configureRouting() {


    routing {
        post("/graphql") {
            call.executeQuery()
        }

        static("/playground") {
            defaultResource("static/graphql/playground.html")
        }

        static("/") {
            BASE_RESOURCES.forEach {
                resource(it, "${RESOURCE_DIRECTORY}/$it")
            }
        }

        static("/static") {
            resources(RESOURCE_STATIC_DIRECTORY)
        }

        static("{...}") {
            defaultResource(RESOURCE_INDEX)
        }
    }
}

