package com.dashboardk.backend.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.*

fun Application.configureCors() {
    install(CORS)
}