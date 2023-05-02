package co.yml.ychat.core.network.factories

import io.ktor.client.HttpClient

public interface HttpClientFactory {
    public fun getHttpClient(): HttpClient
}
