package com.dashboardk.backend

import com.dashboardk.backend.db.DatabaseFactory
import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.CollectorService
import io.ktor.utils.io.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration

object InitService {
    private val collectorService: CollectorService by lazy { inject() }

    @OptIn(DelicateCoroutinesApi::class)
    fun initCollectors() {

        GlobalScope.launch {
            while (true) {
                delay(Duration.ofHours(1))
                try {
                    collectorService.collectDataFlow().collect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun initDB(dbUrl: String, dbPort: String, dbUser: String, dbPassword: String, dbName: String) {
        DatabaseFactory(dbUrl, dbPort, dbUser, dbPassword, dbName)
    }
}
