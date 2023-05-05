package co.yml.ducai.provider.data.infrastructure

import co.yml.ychat.core.network.factories.HttpClientFactory
import co.yml.ychat.core.network.factories.HttpEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class DucAiHttpClient(
    private val httpEngine: HttpClientEngine = HttpEngineFactory.getEngine()
) : HttpClientFactory {

    override fun getHttpClient(): HttpClient {
        return HttpClient(httpEngine) {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = URLProtocol.HTTPS }
                    contentType(ContentType.Application.Json)
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIMEOUT_MILLIS
                connectTimeoutMillis = TIMEOUT_MILLIS
                socketTimeoutMillis = TIMEOUT_MILLIS
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    companion object {
        private const val BASE_URL = "duchaba-yml-text-bert.hf.space"
        private const val TIMEOUT_MILLIS = 60000L
    }
}
