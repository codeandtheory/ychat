package co.yml.ychat.core.network.infrastructure

import co.yml.ychat.core.exceptions.YChatException
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiResultTest {

    @Test
    fun `on isSuccessful when exception is null then should return true`() {
        // arrange
        val apiResult = ApiResult<String>(exception = null)

        // act
        val result = apiResult.isSuccessful

        // assert
        assertEquals(true, result)
    }

    @Test
    fun `on isSuccessful when has exception then should return false`() {
        // arrange
        val apiResult = ApiResult<String>(exception = YChatException())

        // act
        val result = apiResult.isSuccessful

        // assert
        assertEquals(false, result)
    }

    @Test
    fun `on getBodyOrThrow when has body should return retrieved body`() {
        // arrange
        val apiResult = ApiResult(body = "This is a test")

        // act
        val result = apiResult.getBodyOrThrow()

        // assert
        assertEquals("This is a test", result)
    }

    @Test
    fun `on getBodyOrThrow when has no body should throw an exception`() {
        // arrange
        val apiResult = ApiResult(body = null)

        // act
        val result = runCatching { apiResult.getBodyOrThrow() }.exceptionOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `on ensureSuccess when is not successful should throw an exception`() {
        // arrange
        val apiResult = ApiResult<String>(exception = YChatException())

        // act
        val result = runCatching { apiResult.ensureSuccess() }.exceptionOrNull()

        // assert
        assertEquals(true, result != null)
    }
}
