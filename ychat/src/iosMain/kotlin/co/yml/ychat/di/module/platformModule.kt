package co.yml.ychat.di.module

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single { Darwin.create() }
}
