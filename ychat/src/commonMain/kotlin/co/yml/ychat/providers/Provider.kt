package co.yml.ychat.providers

sealed class Provider {
    data class OpenAi(val apiKey: String) : Provider()
    object DucAi : Provider()
}
