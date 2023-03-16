package com.dashboardk.domain.collectors

import com.dashboardk.adapters.GithubNetworkAdapter
import com.dashboardk.di.inject
import com.dashboardk.dtos.GitHubRepoInfoDto
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