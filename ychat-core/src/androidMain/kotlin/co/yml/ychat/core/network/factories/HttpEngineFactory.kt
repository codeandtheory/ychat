package co.yml.ychat.core.network.factories

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

public actual object HttpEngineFactory {
    public actual fun getEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}
