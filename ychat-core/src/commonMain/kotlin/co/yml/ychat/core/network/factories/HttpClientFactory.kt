package co.yml.ychat.core.network.factories

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun getHttpClient(): HttpClient
}
