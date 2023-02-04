package co.yml.ychatgpt.entrypoint

import co.yml.ychatgpt.YChatGpt
import co.yml.ychatgpt.entrypoint.impl.ChatGptImpl
import infrastructure.MockStorage
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

class CompletionIntegrationTest {

    private lateinit var yChatGpt: ChatGptImpl

    @BeforeTest
    fun setup() {
        yChatGpt = YChatGpt.create("api.key") as ChatGptImpl
    }

    @Test
    fun `on completion execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a text"
        val completionSuccessResult = MockStorage.completionSuccessResult(textResult)
        mockHttpEngine(completionSuccessResult)

        // act
        val result = runBlocking {
            yChatGpt.completion()
                .setInput("Say this is a test")
                .execute()
        }

        // assert
        assertEquals("This in indeed a text", result)
    }

    private fun mockHttpEngine(result: String) {
        val httpEngine = MockEngine {
            respond(
                content = result,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val module = module {
            single<HttpClientEngine> { httpEngine }
        }
        yChatGpt.koinApp.modules(module)
    }
}
