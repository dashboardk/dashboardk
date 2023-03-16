package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.adapters.GithubNetworkAdapter
import com.dashboardk.backend.di.inject
import com.dashboardk.backend.dtos.GithubCommitInfoDto
import com.dashboardk.backend.repositories.RepoInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubCommitCollector(private val repoPath: String, private val token: String) : CommitCollector() {

    private val githubNetworkAdapter by lazy {
        inject<GithubNetworkAdapter>()
    }
    private val repoInfoRepository by lazy {
        inject<RepoInfoRepository>()
    }

    override fun getRepoId(): Flow<Long> {
        return repoInfoRepository.getRepo(repoPath).map { it.id }
    }

    override fun collectCommitInfo(): Flow<List<GithubCommitInfoDto>> {
        return githubNetworkAdapter.fetchCommits(repoPath = repoPath, token = token)
    }
}