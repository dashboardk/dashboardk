package com.dashboardk.domain.collectors

import com.dashboardk.di.inject
import com.dashboardk.domain.meta.MetaInfoService

class CollectorService {
    private val metaInfoService by lazy { inject<MetaInfoService>() }
    fun collectData() {

    }
}