package com.dashboardk.adapters

import com.dashboardk.di.inject
import com.dashboardk.dtos.RepoInfoDto
import com.dashboardk.network.ClientProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepoAdapter(private val client: HttpClient) {

    fun fetchRepoDetail(repoPath: String, token: String): Flow<RepoInfoDto> {
        return flow {
            emit(toRepoInfoDto(client.get("https://api.github.com/repos/$repoPath") {
                header("Authorization", "Bearer $token")
            }))
        }
    }

    private suspend fun toRepoInfoDto(response: HttpResponse): RepoInfoDto {
        return response.body()
    }
}