package com.dashboardk.backend.plugins

import com.dashboardk.backend.di.appModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureDi() {
    install(Koin) {
        modules(appModule)
    }
}