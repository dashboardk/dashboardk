package com.dashboardk.domain.meta

import java.io.File

class MetaInfoReader {
    fun readConfig(): String {
        return File("dashboard.yml").readText()
    }
}