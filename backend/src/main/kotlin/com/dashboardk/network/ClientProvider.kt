package com.dashboardk.network

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ClientProvider {
    private val client by lazy {
        HttpClient(Apache) {
            engine {
                followRedirects = true
                socketTimeout = 10_000
                connectTimeout = 10_000
                connectionRequestTimeout = 20_000
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    fun getClientInstance(): HttpClient {
        return client
    }
}