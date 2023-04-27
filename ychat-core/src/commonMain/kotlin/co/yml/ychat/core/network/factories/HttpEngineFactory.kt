package co.yml.ychat.core.network.factories

import io.ktor.client.engine.HttpClientEngine

expect object HttpEngineFactory {
    actual fun getEngine(): HttpClientEngine
}
