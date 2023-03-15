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
    path: dashboardk/backend
    token: dummy
  - name: frontend
    provider: github
    path: dashboardk/frontend
    token: dummy
        """.trimIndent()

        val config = metaInfoParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}