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

class YChatTest {

    private lateinit var yChat: YChatImpl

    @BeforeTest
    fun setup() {
        yChat = YChatImpl("api.key")
    }

    @Test
    fun `on create method should return singleton instance`() {
        // arrange
        val yChatOne = YChat.create("api.key")
        val yChatTwo = YChat.create("api.key")

        // assert
        assertEquals(yChatOne, yChatTwo)
    }

    @Test
    fun `on completion execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a test"
        val completionSuccessResult = MockStorage.completionSuccessResult(textResult)
        mockHttpEngine(completionSuccessResult)

        // act
        val result = runBlocking {
            yChat.completion()
                .setInput("Say this is a test")
                .execute()
        }

        // assert
        assertEquals("This in indeed a test", result)
    }

    @Test
    fun `on chatCompletions execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a test"
        val chatCompletionSuccessResult = MockStorage.chatCompletionsSuccessResult(textResult)
        mockHttpEngine(chatCompletionSuccessResult)

        // act
        val result = runBlocking {
            yChat.chatCompletions()
                .setMaxResults(1)
                .setTemperature(1.0)
                .setTopP(1.0)
                .execute("Say this is a test")
                .first().content
        }

        // assert
        assertEquals("This in indeed a test", result)
    }

    @Test
    fun `on imageGenerations execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a test"
        val imageGenerationsSuccessResult = MockStorage.imageGenerationsSuccessResult(textResult)
        mockHttpEngine(imageGenerationsSuccessResult)

        // act
        val result = runBlocking {
            yChat.imageGenerations()
                .setResults(1)
                .setSize("256x256")
                .setResponseFormat("url")
                .execute("/image ocean")
        }

        // assert
        assertEquals("https://testlink.com/image-test.jps", result.first())
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
