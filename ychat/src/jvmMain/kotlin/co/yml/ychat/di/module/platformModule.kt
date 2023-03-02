package co.yml.ychat.di.module

import io.ktor.client.engine.java.Java
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single { Java.create() }
}
