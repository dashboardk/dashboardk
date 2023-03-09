package com.dashboardk

import com.dashboardk.db.DatabaseFactory
import com.dashboardk.domain.DashboardSystem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration

object InitService {

    @OptIn(DelicateCoroutinesApi::class)
    fun initCollectors() {

        GlobalScope.launch {
            while (true) {
                delay(Duration.ofHours(1))
                DashboardSystem().crawlData()
            }
        }
    }

    fun initDB(dbUrl: String, dbPort: String, dbUser: String, dbPassword: String, dbName:String) {
        DatabaseFactory(dbUrl, dbPort, dbUser, dbPassword, dbName)
    }
}
