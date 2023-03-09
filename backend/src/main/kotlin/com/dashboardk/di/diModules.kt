import com.dashboardk.domain.configs.ConfigFetcher
import com.dashboardk.domain.configs.ConfigParser
import com.dashboardk.domain.configs.ConfigReader
import org.koin.dsl.module

val appModule = module {
    single { ConfigFetcher(ConfigReader(), ConfigParser()) }
}
