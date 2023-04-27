package co.yml.ychat.data.infrastructure

import co.yml.ychat.core.network.factories.HttpClientFactory
import co.yml.ychat.core.network.factories.HttpEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class OpenAiHttpClient(
    private val apiKey: String,
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
                header("Authorization", "Bearer $apiKey")
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
        private const val BASE_URL = "api.openai.com"
        private const val TIMEOUT_MILLIS = 60000L
    }
}
