package com.dashboardk.backend.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureWeb() {
    routing {
        singlePageApplication {
            react("web/src")
        }
    }
}