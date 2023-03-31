package com.dashboardk.backend.domain.meta

import com.dashboardk.backend.ApplicationConfig
import java.io.File

class MetaInfoReader {
    fun readConfig(): String {
        return File(ApplicationConfig.CONFIG_PATH).readText()
    }
}