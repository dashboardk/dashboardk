package com.dashboardk.domain.repo

import com.dashboardk.repositories.RepoInfoRepository
import kotlinx.coroutines.flow.Flow

class RepoService(private val repoInfoRepository: RepoInfoRepository) {

    fun getRepos(): Flow<List<Repo>> {
        return repoInfoRepository.getRepo()
    }
}