package com.dashboardk.domain.collectors

import com.dashboardk.adapters.GithubNetworkAdapter
import com.dashboardk.di.inject
import com.dashboardk.dtos.GithubCommitInfoDto
import com.dashboardk.repositories.RepoInfoRepository
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