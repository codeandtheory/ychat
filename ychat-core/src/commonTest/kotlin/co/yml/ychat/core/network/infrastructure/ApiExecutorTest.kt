package co.yml.ychat.core.network.infrastructure

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.decodeURLPart
import io.ktor.http.headersOf
import io.ktor.utils.io.errors.IOException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class ApiExecutorTest {

    @Test
    fun `on execute when request succeed then should return success payload`() = runBlocking {
        // arrange
        var endpoint = ""
        var httpMethod = ""
        val mockEngine = MockEngine { request ->
            endpoint = request.url.toString().decodeURLPart()
            httpMethod = request.method.value
            respond(
                content = "This is a test",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = MockHttpClient(mockEngine)
        val apiExecutor = ApiExecutor(httpClient)

        // act
        val result = apiExecutor
            .setEndpoint("api/test")
            .setHttpMethod(HttpMethod.Post)
            .addQuery("query1", "query_value")
            .addQuery("query2", listOf("query1", "query2"))
            .execute<String>()

        // assert
        assertEquals("http://localhost/api/test?query1=query_value&query2=query1,query2", endpoint)
        assertEquals("POST", httpMethod)
        assertEquals("This is a test", result.body)
    }

    @Test
    fun `on execute when request is multipart type then content type should be multipart type`() = runBlocking {
        // arrange
        var endpoint = ""
        var httpMethod = ""
        var contentType: String? = null
        val mockEngine = MockEngine { request ->
            endpoint = request.url.toString().decodeURLPart()
            httpMethod = request.method.value
            contentType = request.body.contentType?.contentType
            respond(
                content = "This is a test",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = MockHttpClient(mockEngine)
        val apiExecutor = ApiExecutor(httpClient)

        // act
        apiExecutor
            .setEndpoint("api/test")
            .setHttpMethod(HttpMethod.Post)
            .addFormPart("key1", "value1")
            .addFormPart("key1", "file_mock", ByteArray(100))
            .execute<String>()

        // assert
        assertEquals("http://localhost/api/test", endpoint)
        assertEquals("POST", httpMethod)
        assertEquals("multipart", contentType)
    }

    @Test
    fun `on execute when occurs api error then should return error as expected`() = runBlocking {
        // arrange
        val mockEngine = MockEngine {
            respond(
                content = "",
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = MockHttpClient(mockEngine)
        val apiExecutor = ApiExecutor(httpClient)

        // act
        val result = apiExecutor
            .setEndpoint("api/test")
            .setHttpMethod(HttpMethod.Post)
            .execute<String>()

        // assert
        assertEquals(400, result.statusCode)
    }

    @Test
    fun `on execute when request failed then should return error as expected`() = runBlocking {
        // arrange
        val mockEngine = MockEngine {
            throw IOException("Error")
        }
        val httpClient = MockHttpClient(mockEngine)
        val apiExecutor = ApiExecutor(httpClient)

        // act
        val result = apiExecutor
            .setEndpoint("api/test")
            .setHttpMethod(HttpMethod.Post)
            .execute<String>()

        // assert
        assertEquals(false, result.isSuccessful)
    }
}
