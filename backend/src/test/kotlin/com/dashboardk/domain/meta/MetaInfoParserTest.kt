package com.dashboardk.domain.meta

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MetaInfoParserTest {

    private val metaInfoParser = MetaInfoParser()

    @Test
    fun shouldReturnConfigForGivenString() {
        val configString = """
repos:
  - name: dashboardk/backend
    provider: github
    token: dummy
  - name: dashboardk/frontend
    provider: github
    token: dummy
        """.trimIndent()

        val config = metaInfoParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}