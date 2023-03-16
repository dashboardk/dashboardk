package com.dashboardk.backend.domain.repo

import com.dashboardk.backend.repositories.RepoInfoRepository
import kotlinx.coroutines.flow.Flow

class RepoService(private val repoInfoRepository: RepoInfoRepository) {

    fun getRepos(): Flow<List<Repo>> {
        return repoInfoRepository.getRepos()
    }
}