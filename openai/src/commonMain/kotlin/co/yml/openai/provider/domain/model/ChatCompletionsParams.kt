package co.yml.openai.provider.domain.model

internal data class ChatCompletionsParams(
    var messages: ArrayList<ChatMessage> = arrayListOf(),
    var model: String = "gpt-3.5-turbo",
    var maxResults: Int = 1,
    var maxTokens: Int = 4096,
    var temperature: Double = 1.0,
    var topP: Double = 1.0,
)
