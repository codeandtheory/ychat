package co.yml.ychatgpt.data.infrastructure

import co.yml.ychatgpt.data.exception.ChatGptException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.util.toMap
import io.ktor.utils.io.errors.IOException
import kotlin.collections.set

internal class ApiExecutor(private val httpClient: HttpClient) {

    private lateinit var endpoint: String

    private lateinit var httpMethod: HttpMethod

    private var body: Any = EmptyContent

    private var query: HashMap<String, String> = HashMap()

    fun setEndpoint(endpoint: String): ApiExecutor {
        this.endpoint = endpoint
        return this
    }

    fun setHttpMethod(httpMethod: HttpMethod): ApiExecutor {
        this.httpMethod = httpMethod
        return this
    }

    fun setBody(body: Any): ApiExecutor {
        this.body = body
        return this
    }

    fun addQuery(key: String, value: String): ApiExecutor {
        this.query[key] = value
        return this
    }

    fun addQuery(key: String, value: List<String>): ApiExecutor {
        this.query[key] = value.joinToString(",")
        return this
    }

    suspend inline fun <reified T> execute(): ApiResult<T> {
        return try {
            val response = httpClient.request(endpoint) {
                url { query.forEach { parameters.append(it.key, it.value) } }
                method = httpMethod
                setBody(this@ApiExecutor.body)
            }
            return response.toApiResult()
        } catch (responseException: ResponseException) {
            responseException.toApiResult()
        } catch (iOException: IOException) {
            iOException.toApiResult()
        }
    }

    private suspend inline fun <reified T> HttpResponse.toApiResult(): ApiResult<T> {
        val headers = this.headers.toMap()
        val statusCode = this.status.value
        return if (!this.status.isSuccess()) {
            val exception = ChatGptException(null, statusCode)
            ApiResult(null, headers, statusCode, exception)
        } else {
            ApiResult(this.body<T>(), headers, statusCode, null)
        }
    }

    private fun <T> ResponseException.toApiResult(): ApiResult<T> {
        return ApiResult(
            statusCode = this.response.status.value,
            exception = ChatGptException(this.cause, this.response.status.value)
        )
    }

    private fun <T> Throwable.toApiResult(): ApiResult<T> {
        return ApiResult(
            statusCode = null,
            exception = ChatGptException(this.cause)
        )
    }
}
