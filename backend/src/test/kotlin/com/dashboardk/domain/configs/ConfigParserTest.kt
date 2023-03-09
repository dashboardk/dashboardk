package com.dashboardk.domain.configs

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConfigParserTest {

    private val configParser = ConfigParser()

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

        val config = configParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}