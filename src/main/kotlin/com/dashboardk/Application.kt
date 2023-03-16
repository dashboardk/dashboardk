package com.dashboardk

import com.dashboardk.graphql.configureGraphQL
import com.dashboardk.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 80, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureStatusPage()
    configureDi()
    configureGraphQL()

    InitService.initDB(
        dbUrl = ApplicationConfig.DB_HOST,
        dbPort = ApplicationConfig.DB_PORT,
        dbUser = ApplicationConfig.DB_USER_NAME,
        dbPassword = ApplicationConfig.DB_PASSWORD,
        dbName = ApplicationConfig.DB_NAME
    )
    InitService.initCollectors()
}
