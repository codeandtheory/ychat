package co.yml.ychat.core.network.infrastructure

import co.yml.ychat.core.exceptions.YChatException

/** Encapsulates an outcome from source api. */
data class ApiResult<T>(
    val body: T? = null,
    val headers: Map<String, List<String>> = mapOf(),
    val statusCode: Int? = null,
    val exception: YChatException? = null,
) {

    /** Return true if the api outcome was successful. */
    val isSuccessful: Boolean = exception == null

    /** Try to get [body], if it is null an [YChatException] will be thrown. */
    fun getBodyOrThrow(): T {
        val exception = exception
            ?: YChatException("Could not retrieve body from ApiResult.")
        return body ?: throw exception
    }

    /** Throw an [exception] when [isSuccessful] is false. */
    fun ensureSuccess() {
        if (!isSuccessful)
            throw exception ?: YChatException("Api request was not successful.")
    }
}
