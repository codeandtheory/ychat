package co.yml.ychatgpt.di.module

import io.ktor.client.engine.android.Android
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single { Android.create() }
}