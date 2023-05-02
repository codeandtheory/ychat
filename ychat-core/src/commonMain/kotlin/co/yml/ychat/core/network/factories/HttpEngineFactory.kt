package co.yml.ychat.core.network.factories

import io.ktor.client.engine.HttpClientEngine

public expect object HttpEngineFactory {
    public actual fun getEngine(): HttpClientEngine
}
