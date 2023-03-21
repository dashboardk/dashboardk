package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.adapters.GithubNetworkAdapter
import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.infos.RepoInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubRepoCollector(private val repoPath: String, private val token: String) :
    RepoCollector() {

    private val githubNetworkAdapter by lazy {
        inject<GithubNetworkAdapter>()
    }

    override fun collectRepoInfo(): Flow<RepoInfo> {
        return githubNetworkAdapter.fetchRepoDetail(repoPath = repoPath, token = token).map { dto ->
            RepoInfo(name = dto.fullName)
        }
    }
}