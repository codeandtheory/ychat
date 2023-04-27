package co.yml.ychat.data.dto

import co.yml.ychat.core.model.FileBytes

internal data class AudioParamsDto(
    val filename: String,
    val byteArray: FileBytes,
    val model: String,
    val prompt: String,
    val responseFormat: String,
    val temperature: Double,
    val language: String,
) {

    fun getMap(): Map<String, Any> {
        return mapOf(
            "model" to model,
            "prompt" to prompt,
            "response_format" to responseFormat,
            "temperature" to temperature,
            "language" to language,
        )
    }
}
