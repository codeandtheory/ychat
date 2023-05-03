package co.yml.ychat.providers

sealed class Provider {
    data class OpenAiP(val apiKey: String) : Provider()
    object DucAiP : Provider()
}
