package com.dashboardk.backend.domain.collectors

import com.dashboardk.backend.adapters.GithubNetworkAdapter
import com.dashboardk.backend.di.inject
import com.dashboardk.backend.domain.collectors.infos.CommitInfo
import com.dashboardk.backend.repositories.RepoInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant

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

    override fun collectCommitInfo(): Flow<List<CommitInfo>> {
        return githubNetworkAdapter.fetchCommits(repoPath = repoPath, token = token).map { dtos ->
            dtos.map { dto ->
                CommitInfo(
                    sha = dto.sha,
                    message = dto.commit.message,
                    committedBy = dto.commit.author.name,
                    time = Instant.parse(dto.commit.author.date)
                )
            }
        }
    }
}