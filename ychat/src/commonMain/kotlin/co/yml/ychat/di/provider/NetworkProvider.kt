package co.yml.ychat.di.provider

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object NetworkProvider {

    private const val BASE_URL = "api.openai.com"
    private const val TIMEOUT_MILLIS = 60000L

    fun provideHttpClient(
        engine: HttpClientEngine,
        apiKey: String? = null,
        baseUrl: String = BASE_URL
    ): HttpClient {
        return HttpClient(engine) {
            defaultRequest {
                url {
                    host = baseUrl
                    url { protocol = URLProtocol.HTTPS }
                    contentType(ContentType.Application.Json)
                }
                apiKey?.let {
                    header("Authorization", "Bearer $it")
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
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}
