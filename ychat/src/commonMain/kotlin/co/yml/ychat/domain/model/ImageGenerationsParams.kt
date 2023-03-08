package co.yml.ychat.domain.model

internal data class ImageGenerationsParams(
    var prompt: String = "",
    var results: Int = 2,
    var size: String = "256x256",
    var responseFormat: String = "url",
    var user: String = "",
)
