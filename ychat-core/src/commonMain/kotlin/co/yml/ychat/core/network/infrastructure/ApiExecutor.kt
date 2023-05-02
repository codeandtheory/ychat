package co.yml.ychat.core.network.infrastructure

import co.yml.ychat.core.network.extensions.toApiResult
import co.yml.ychat.core.network.factories.HttpClientFactory
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.utils.io.errors.IOException
import kotlin.collections.set

public class ApiExecutor(private val httpClientFactory: HttpClientFactory) {

    private val httpClient by lazy { httpClientFactory.getHttpClient() }

    private lateinit var endpoint: String

    private lateinit var httpMethod: HttpMethod

    private var body: Any = EmptyContent

    private var query: HashMap<String, String> = HashMap()

    public val formParts: MutableList<FormPart<*>> = mutableListOf()

    public fun setEndpoint(endpoint: String): ApiExecutor {
        this.endpoint = endpoint
        return this
    }

    public fun setHttpMethod(httpMethod: HttpMethod): ApiExecutor {
        this.httpMethod = httpMethod
        return this
    }

    public fun setBody(body: Any): ApiExecutor {
        this.body = body
        return this
    }

    public fun addQuery(key: String, value: String): ApiExecutor {
        this.query[key] = value
        return this
    }

    public fun addQuery(key: String, value: List<String>): ApiExecutor {
        this.query[key] = value.joinToString(",")
        return this
    }

    public fun <T : Any> addFormPart(key: String, value: T): ApiExecutor {
        formParts += FormPart(key, value)
        return this
    }

    public fun addFormPart(key: String, fileName: String, value: ByteArray): ApiExecutor {
        val headers = Headers.build {
            append(HttpHeaders.ContentType, ContentType.Application.OctetStream.contentType)
            append(HttpHeaders.ContentDisposition, "filename=$fileName")
        }
        formParts += FormPart(key, value, headers = headers)
        return this
    }

    public suspend inline fun <reified T> execute(): ApiResult<T> {
        return try {
            val response = if (formParts.isEmpty()) executeRequest() else executeRequestAsForm()
            return response.toApiResult()
        } catch (responseException: ResponseException) {
            responseException.toApiResult()
        } catch (iOException: IOException) {
            iOException.toApiResult()
        }
    }

    public suspend fun executeRequest(): HttpResponse {
        return httpClient.request(endpoint) {
            url { query.forEach { parameters.append(it.key, it.value) } }
            method = httpMethod
            this.setBody(this@ApiExecutor.body)
        }
    }

    public suspend fun executeRequestAsForm(): HttpResponse {
        return httpClient.submitFormWithBinaryData(
            url = endpoint,
            formData = formData { formParts.forEach { append(it) } }
        )
    }
}
