package com.dashboardk.domain.meta

class MetaInfoService(private val metaInfoReader: MetaInfoReader, private val metaInfoParser: MetaInfoParser) {

    fun getDashboardConfig(): MetaInfo {
        return metaInfoParser.parseConfig(metaInfoReader.readConfig())
    }
}