package com.dashboardk.domain.meta

import com.dashboardk.backend.domain.meta.MetaInfoParser
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MetaInfoParserTest {

    private val metaInfoParser = MetaInfoParser()

    @Test
    fun shouldReturnConfigForGivenString() {
        val configString = """
repos:
  - name: dashboardk/dashboardk
    provider: github
    token: token
widgets:
  - !<CommitCount>
    name: CommitCount
    type: CommitCount
    repoName: dashboardk/dashboardk
        """.trimIndent()

        val config = metaInfoParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}