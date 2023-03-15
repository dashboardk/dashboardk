import com.dashboardk.adapters.GithubNetworkAdapter
import com.dashboardk.domain.meta.MetaInfoService
import com.dashboardk.domain.meta.MetaInfoParser
import com.dashboardk.domain.meta.MetaInfoReader
import com.dashboardk.network.ClientProvider
import com.dashboardk.repositories.CommitRepository
import com.dashboardk.repositories.RepoInfoRepository
import org.koin.dsl.module

val appModule = module {
    single { MetaInfoService(MetaInfoReader(), MetaInfoParser()) }
    single { ClientProvider.getClientInstance() }
    factory { RepoInfoRepository() }
    factory { CommitRepository() }
    factory { GithubNetworkAdapter(get()) }
}
