package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.domain.model.FileBytes
import kotlin.coroutines.cancellation.CancellationException

interface AudioTranslations {

    /**
     * Sets the ID of the model to use. The default value is "whisper-1".
     * @param model The ID of the model to use.
     * @return The updated [AudioTranslations] object with the new model ID.
     */
    fun setModel(model: String): AudioTranslations

    /**
     * Sets an optional text to guide the model's style or continue a previous audio segment.
     * The [prompt] should be in English.
     * @param prompt The prompt to use.
     * @return The updated [AudioTranslations] object with the new prompt.
     */
    fun setPrompt(prompt: String): AudioTranslations

    /**
     * Sets the format of the transcript output.
     * @param format The format of the transcript output, in one of these options:
     * json, text, srt, verbose_json, or vtt.
     * @return The updated [AudioTranslations] object with the new response format.
     */
    fun setResponseFormat(format: String): AudioTranslations

    /**
     * Sets the sampling temperature, between 0 and 1. Higher values like 0.8 will make the output
     * more random, while lower values like 0.2 will make it more focused and deterministic.
     * If set to 0, the model will use log probability to automatically increase the temperature
     * until certain thresholds are hit.
     * @param temperature The sampling temperature to use.
     * @return The updated [AudioTranslations] object with the new temperature.
     */
    fun setTemperature(temperature: Double): AudioTranslations

    /**
     * Translates audio into English.
     * @param filename The name of the audio file.
     * @param audioFile The audio file to translate, in one of these formats: mp3, mp4, mpeg,
     * mpga, m4a, wav, or webm.
     * @return The transcript output in the specified format.
     * @throws CancellationException if the operation is cancelled.
     * @throws ChatGptException if there is an error transcribing the audio file.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(filename: String, audioFile: FileBytes): String

    /**
     * Translates audio into English and passes it to the provided [callback].
     * @param filename The name of the audio file.
     * @param audioFile The audio file to translate, in one of these formats:
     * mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @param callback The callback to receive the transcript output in the specified format.
     */
    fun execute(filename: String, audioFile: FileBytes, callback: YChat.Callback<String>)
}
