package co.yml.ychat.entrypoint

import co.yml.ychat.YChat
import co.yml.ychat.entrypoint.impl.YChatImpl
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

    private lateinit var yChat: YChatImpl

    @BeforeTest
    fun setup() {
        yChat = YChat.create("api.key") as YChatImpl
    }

    @Test
    fun `on completion execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a text"
        val completionSuccessResult = MockStorage.completionSuccessResult(textResult)
        mockHttpEngine(completionSuccessResult)

        // act
        val result = runBlocking {
            yChat.completion()
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
        yChat.koinApp.modules(module)
    }
}
