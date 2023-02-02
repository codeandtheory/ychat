package co.yml.ychatgpt.di

import co.yml.ychatgpt.di.provider.NetworkProvider
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.request
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class NetworkProviderTest {

    @Test
    fun `on provideHttpClient should assert default request`() {
        // arrange
        var baseUrl = ""
        var headers: Headers? = null
        val apiKey = "tok123"
        val mockEngine = MockEngine { request ->
            baseUrl = request.url.toString()
            headers = request.headers
            respond(
                content = "This is a test",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        // act
        runBlocking { NetworkProvider.provideHttpClient(mockEngine, apiKey).request() }

        // assert
        assertEquals("https://api.openai.com", baseUrl)
        assertEquals("Bearer tok123", headers?.get("Authorization"))
    }
}
