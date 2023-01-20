package co.yml.ychatgpt.data.infrastructure

import co.yml.ychatgpt.data.exception.ChatGptException

/** Encapsulates an outcome from source api. */
internal data class ApiResult<T>(
    val body: T? = null,
    val headers: Map<String, List<String>> = mapOf(),
    val statusCode: Int? = null,
    val exception: ChatGptException? = null,
) {

    /** Return true if the api outcome was successful. */
    val isSuccessful: Boolean = exception == null

    /** Try to get [body], if it is null an [ChatGptException] will be thrown. */
    fun getBodyOrThrow(): T {
        val exception = exception
            ?: ChatGptException("Could not retrieve body from ApiResult.")
        return body ?: throw exception
    }

    /** Throw an [exception] when [isSuccessful] is false. */
    fun ensureSuccess() {
        if (!isSuccessful)
            throw exception ?: ChatGptException("Api request was not successful.")
    }
}
