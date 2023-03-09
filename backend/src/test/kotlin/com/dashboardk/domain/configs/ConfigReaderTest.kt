package com.dashboardk.domain.configs

import com.google.common.truth.Truth
import org.junit.Test

class ConfigReaderTest {

    private val configReader = ConfigReader()

    @Test
    fun shouldReturnConfigFromFile() {
        val config = configReader.readConfig()
        Truth.assertThat(config).isNotNull()
        Truth.assertThat(config).isEqualTo(
            """
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
        )
    }
}