package co.yml.ychat.domain.model

internal data class AudioParams(
    var model: String = "whisper-1",
    var prompt: String = "",
    var responseFormat: String = "json",
    var temperature: Double = 0.0,
    var language: String = "en"
)
