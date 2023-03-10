package com.dashboardk.domain.meta

import com.google.common.truth.Truth
import org.junit.Test

class MetaInfoReaderTest {

    private val metaInfoReader = MetaInfoReader()

    @Test
    fun shouldReturnConfigFromFile() {
        val config = metaInfoReader.readConfig()
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