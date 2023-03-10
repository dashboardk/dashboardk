import com.dashboardk.domain.meta.MetaInfoService
import com.dashboardk.domain.meta.MetaInfoParser
import com.dashboardk.domain.meta.MetaInfoReader
import org.koin.dsl.module

val appModule = module {
    single { MetaInfoService(MetaInfoReader(), MetaInfoParser()) }
}
