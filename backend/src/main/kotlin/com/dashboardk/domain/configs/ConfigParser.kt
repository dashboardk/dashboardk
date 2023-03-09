package com.dashboardk.domain.configs

import com.charleskorn.kaml.Yaml

class ConfigParser {
    fun parseConfig(config: String): DashboardConfig {
        return Yaml.default.decodeFromString(DashboardConfig.serializer(), config)
    }
}