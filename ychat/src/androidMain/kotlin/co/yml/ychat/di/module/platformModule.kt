package co.yml.ychat.di.module

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single { OkHttp.create() }
}
