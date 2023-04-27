package co.yml.ychat.core.network.infrastructure

import co.yml.ychat.core.network.factories.HttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine

class MockHttpClient(private val mockEngine: MockEngine) : HttpClientFactory {
    override fun getHttpClient(): HttpClient {
        return HttpClient(mockEngine)
    }
}
