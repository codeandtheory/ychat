package co.yml.ychat.data.infrastructure

import co.yml.ychat.data.exception.ChatGptException
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.util.toMap

internal suspend inline fun <reified T> HttpResponse.toApiResult(): ApiResult<T> {
    val headers = this.headers.toMap()
    val statusCode = this.status.value
    return if (!this.status.isSuccess()) {
        val exception = ChatGptException(null, statusCode)
        ApiResult(null, headers, statusCode, exception)
    } else {
        ApiResult(this.body<T>(), headers, statusCode, null)
    }
}

internal fun <T> ResponseException.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = this.response.status.value,
        exception = ChatGptException(this.cause, this.response.status.value)
    )
}

internal fun <T> Throwable.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = null,
        exception = ChatGptException(this.cause)
    )
}
