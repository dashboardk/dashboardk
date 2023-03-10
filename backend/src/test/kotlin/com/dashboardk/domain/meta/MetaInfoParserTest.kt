package com.dashboardk.domain.meta

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MetaInfoParserTest {

    private val metaInfoParser = MetaInfoParser()

    @Test
    fun shouldReturnConfigForGivenString() {
        val configString = """
repos:
  - name: backend
    provider: github
    url: https://github.com/dashboardk/dashboardk
    token: dummy
  - name: frontend
    provider: github
    url: https://github.com/dashboardk/dashboardk
    token: dummy
        """.trimIndent()

        val config = metaInfoParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}