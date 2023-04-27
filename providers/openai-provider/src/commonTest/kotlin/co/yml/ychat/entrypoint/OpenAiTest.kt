package co.yml.ychat.entrypoint

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.data.infrastructure.OpenAiHttpClient
import co.yml.openai.provider.entrypoint.impl.OpenAiImpl
import co.yml.ychat.core.model.FileBytes
import co.yml.ychat.core.network.factories.HttpClientFactory
import infrastructure.MockStorage
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

class OpenAiTest {

    private lateinit var openAi: OpenAiImpl

    @BeforeTest
    fun setup() {
        openAi = OpenAiImpl("api.key")
    }

    @Test
    fun `on create method should return singleton instance`() {
        // arrange
        val openAiOne = OpenAi.create("api.key")
        val openAiTwo = OpenAi.create("api.key")

        // assert
        assertEquals(openAiOne, openAiTwo)
    }

    @Test
    fun `on completion execute method should return result successfully`() {
        // arrange
        val textResult = "This in indeed a test"
        val completionSuccessResult = MockStorage.completionSuccessResult(textResult)
        mockHttpEngine(completionSuccessResult)

        // act
        val result = runBlocking {
            openAi.completion()
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
            openAi.chatCompletions()
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
            openAi.imageGenerations()
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
            openAi.edits()
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
        val result = runBlocking { openAi.listModels().execute() }

        // assert
        assertEquals(expectedResult, result.first().id)
    }

    @Test
    fun `on retrieveModel execute method should return result successfully`() {
        // arrange
        val expectedResult = "model1"
        val modelSuccessResult = MockStorage.modelSuccessResult(expectedResult)
        mockHttpEngine(modelSuccessResult)

        // act
        val result = runBlocking { openAi.retrieveModel().execute(expectedResult) }

        // assert
        assertEquals(expectedResult, result.id)
    }

    @Test
    fun `on audioTranscriptions execute method should return result successfully`() {
        // arrange
        val expectedResult = "This is a test."
        val imageGenerationsSuccessResult =
            MockStorage.audioSuccessResult(expectedResult)
        val audioFile = ByteArray(1024) as FileBytes
        mockHttpEngine(imageGenerationsSuccessResult)

        // act
        val result = runBlocking {
            openAi.audioTranscriptions()
                .setTemperature(0.0)
                .setModel("model-1")
                .setPrompt("Test")
                .setResponseFormat("json")
                .setLanguage("en")
                .execute("file.mp4", audioFile)
        }

        // assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun `on audioTranslations execute method should return result successfully`() {
        // arrange
        val expectedResult = "This is a test."
        val imageGenerationsSuccessResult =
            MockStorage.audioSuccessResult(expectedResult)
        val audioFile = ByteArray(1024) as FileBytes
        mockHttpEngine(imageGenerationsSuccessResult)

        // act
        val result = runBlocking {
            openAi.audioTranslations()
                .setTemperature(0.0)
                .setModel("model-1")
                .setPrompt("Test")
                .setResponseFormat("json")
                .execute("file.mp4", audioFile)
        }

        // assert
        assertEquals(expectedResult, result)
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
            single<HttpClientFactory> { OpenAiHttpClient("api.key", httpEngine) }
        }
        openAi.koinApp.modules(module)
    }
}
