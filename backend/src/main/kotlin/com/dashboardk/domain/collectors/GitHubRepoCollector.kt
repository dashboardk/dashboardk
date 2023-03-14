package com.dashboardk.domain.collectors

import com.dashboardk.adapters.GithubRepoAdapter
import com.dashboardk.di.inject
import com.dashboardk.dtos.RepoInfoDto
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow

class GitHubRepoCollector(private val repoName: String, private val token: String, private val repoPath: String) :
    RepoCollector() {

    private val githubRepoAdapter by lazy {
        inject<GithubRepoAdapter>()
    }

    override fun collectData(): Flow<RepoInfoDto> {
        return githubRepoAdapter.fetchRepoDetail(repoPath = repoPath, token = token)
    }
}