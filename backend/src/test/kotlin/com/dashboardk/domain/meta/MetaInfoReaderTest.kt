package com.dashboardk.domain.meta

import com.dashboardk.backend.domain.meta.MetaInfoReader
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
  - name: dashboardk/backend
    provider: github
    token: token
widgets:
  - !<CommitCount>
    name: CommitCount
    type: CommitCount
    repoName: dashboardk/backend
        """.trimIndent()
        )
    }
}