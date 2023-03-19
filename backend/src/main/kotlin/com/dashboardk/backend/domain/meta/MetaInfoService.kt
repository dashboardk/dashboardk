package com.dashboardk.backend.domain.meta

import com.dashboardk.backend.di.inject


class MetaInfoService(private val metaInfoReader: MetaInfoReader, private val metaInfoParser: MetaInfoParser) {

    fun getDashboardMetaInfo(): MetaInfo {
        return metaInfoParser.parseConfig(metaInfoReader.readConfig())
    }
}