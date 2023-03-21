package com.dashboardk.backend.di

import com.dashboardk.backend.adapters.GithubNetworkAdapter
import com.dashboardk.backend.domain.meta.MetaInfoParser
import com.dashboardk.backend.domain.meta.MetaInfoReader
import com.dashboardk.backend.domain.meta.MetaInfoService
import com.dashboardk.backend.network.ClientProvider
import com.dashboardk.backend.repositories.CollaboratorRepository
import com.dashboardk.backend.repositories.CommitRepository
import com.dashboardk.backend.repositories.RepoInfoRepository
import org.koin.dsl.module

val appModule = module {
    single { ClientProvider.getClientInstance() }
    factory { MetaInfoReader() }
    factory { MetaInfoParser() }
    factory { RepoInfoRepository() }
    factory { CommitRepository(get()) }
    factory { CollaboratorRepository() }
    factory { MetaInfoService(get(), get()) }
    factory { GithubNetworkAdapter(get()) }
}
