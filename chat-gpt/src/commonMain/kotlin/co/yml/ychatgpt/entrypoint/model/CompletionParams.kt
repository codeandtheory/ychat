package co.yml.ychatgpt.entrypoint.model

data class CompletionParams(
    var model: String = "text-davinci-003",
    var maxTokens: Int = 150,
    var temperature: Double = 1.0,
    var topP: Double = 1.0,
)
