package co.yml.ychatgpt.entrypoint

data class CompletionParams(
    var maxTokens: Int = 2048,
    var temperature: Double = 1.0,
    var topP: Double = 1.0
)
