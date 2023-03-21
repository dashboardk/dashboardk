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
  - name: CommitCount
    type: CommitCount
    repoName: dashboardk/dashboardk
    branchName: all  
        """.trimIndent()

        val config = metaInfoParser.parseConfig(configString)
        assertThat(config).isNotNull()
    }
}