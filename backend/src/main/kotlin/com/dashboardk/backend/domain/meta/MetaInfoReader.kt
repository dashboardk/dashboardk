package com.dashboardk.backend.domain.meta

import java.io.FileNotFoundException

class MetaInfoReader {
    fun readConfig(): String {
        return this.javaClass.classLoader.getResource("dashboard.yml")?.readText() ?: throw FileNotFoundException("dashboard file not found")
    }
}