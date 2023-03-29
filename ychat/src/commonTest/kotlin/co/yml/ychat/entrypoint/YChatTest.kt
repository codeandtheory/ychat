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
        val imageUrl = "https://testlink.com/image-test.jpg"
        val imageGenerationsSuccessResult = MockStorage.imageGenerationsSuccessResult(imageUrl)
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
        assertEquals("https://testlink.com/image-test.jpg", result.first())
    }

    @Test
    fun `on edits execute method should return result successfully`() {
        // arrange
        val expectedResult = "What day of the week is it?"
        val imageGenerationsSuccessResult = MockStorage.editsSuccessResult(expectedResult)
        mockHttpEngine(imageGenerationsSuccessResult)

        // act
        val result = runBlocking {
            yChat.edits()
                .setResults(1)
                .setTemperature(1.0)
                .setModel("model-1")
                .setTopP(1.0)
                .setInput("What day of the wek is it?")
                .execute("Fix the spelling mistakes")
        }

        // assert
        assertEquals(expectedResult, result.first())
    }

    @Test
    fun `on listModels execute method should return result successfully`() {
        // arrange
        val expectedResult = "model1"
        val listModelsSuccessResult = MockStorage.listModelsSuccessResult(expectedResult)
        mockHttpEngine(listModelsSuccessResult)

        // act
        val result = runBlocking { yChat.listModels().execute() }

        // assert
        assertEquals(expectedResult, result.first().id)
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
