package com.dashboardk.domain.configs

class ConfigFetcher(private val configReader: ConfigReader, private val configParser: ConfigParser) {

    fun getDashboardConfig(): DashboardConfig {
        return configParser.parseConfig(configReader.readConfig())
    }
}