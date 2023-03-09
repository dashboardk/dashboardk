package com.dashboardk.domain.configs

import java.io.File

class ConfigReader {
    fun readConfig(): String {
        return File("dashboard.yml").readText()
    }
}