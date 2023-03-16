package com.dashboardk.backend.domain.meta

import com.charleskorn.kaml.Yaml

class MetaInfoParser {
    fun parseConfig(config: String): MetaInfo {
        return Yaml.default.decodeFromString(MetaInfo.serializer(), config)
    }
}