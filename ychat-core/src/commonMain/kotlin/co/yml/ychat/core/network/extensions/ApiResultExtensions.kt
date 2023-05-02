package co.yml.ychat.core.network.extensions

import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.util.toMap

public suspend inline fun <reified T> HttpResponse.toApiResult(): ApiResult<T> {
    val headers = this.headers.toMap()
    val statusCode = this.status.value
    return if (!this.status.isSuccess()) {
        val errorMessage = this.bodyAsText()
        val exception = YChatException(errorMessage, statusCode = statusCode)
        ApiResult(null, headers, statusCode, exception)
    } else {
        ApiResult(this.body<T>(), headers, statusCode, null)
    }
}

public fun <T> ResponseException.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = this.response.status.value,
        exception = YChatException(this.cause, this.response.status.value)
    )
}

public fun <T> Throwable.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = null,
        exception = YChatException(this.cause)
    )
}
