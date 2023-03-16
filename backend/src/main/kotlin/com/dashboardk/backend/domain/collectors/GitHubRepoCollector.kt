package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.adapters.GithubNetworkAdapter
import com.dashboardk.backend.di.inject
import com.dashboardk.backend.dtos.GitHubRepoInfoDto
import kotlinx.coroutines.flow.Flow

class GitHubRepoCollector(private val repoPath: String, private val token: String, ) :
    RepoCollector() {

    private val githubNetworkAdapter by lazy {
        inject<GithubNetworkAdapter>()
    }

    override fun collectRepoInfo(): Flow<GitHubRepoInfoDto> {
        return githubNetworkAdapter.fetchRepoDetail(repoPath = repoPath, token = token)
    }
}