package co.yml.ychat.core.network.factories

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual object HttpEngineFactory {

    actual fun getEngine(): HttpClientEngine {
        return Darwin.create()
    }
}
