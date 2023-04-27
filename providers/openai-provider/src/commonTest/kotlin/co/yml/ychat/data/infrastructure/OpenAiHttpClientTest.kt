package co.yml.ychat.data.infrastructure

import co.yml.openai.provider.data.infrastructure.OpenAiHttpClient
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

class OpenAiHttpClientTest {

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
        runBlocking { OpenAiHttpClient(apiKey, mockEngine).getHttpClient().request() }

        // assert
        assertEquals("https://api.openai.com", baseUrl)
        assertEquals("Bearer tok123", headers?.get("Authorization"))
    }
}
