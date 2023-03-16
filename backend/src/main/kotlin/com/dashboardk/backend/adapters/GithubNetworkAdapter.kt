package com.dashboardk.backend.adapters

import com.dashboardk.backend.dtos.GithubCommitInfoDto
import com.dashboardk.backend.dtos.GitHubRepoInfoDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubNetworkAdapter(private val client: HttpClient) {

    fun fetchRepoDetail(repoPath: String, token: String): Flow<GitHubRepoInfoDto> {
        return flow {
            emit(client.get("https://api.github.com/repos/$repoPath") {
                header("Authorization", "Bearer $token")
            }.body())
        }
    }

    fun fetchCommits(repoPath: String, token: String): Flow<List<GithubCommitInfoDto>> {
        return flow {
            emit(client.get("https://api.github.com/repos/$repoPath/commits") {
                header("Authorization", "Bearer $token")
            }.body())
        }
    }
}