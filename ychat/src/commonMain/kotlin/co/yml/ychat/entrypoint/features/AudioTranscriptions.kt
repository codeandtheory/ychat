package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.domain.model.FileBytes
import kotlin.coroutines.cancellation.CancellationException

interface AudioTranscriptions {

    /**
     * Sets the ID of the model to use. The default value is "whisper-1".
     * @param model The ID of the model to use.
     * @return The updated [AudioTranscriptions] object with the new model ID.
     */
    fun setModel(model: String): AudioTranscriptions

    /**
     * Sets an optional text to guide the model's style or continue a previous audio segment.
     * The prompt should match the audio language.
     * @param prompt The prompt to use.
     * @return The updated [AudioTranscriptions] object with the new prompt.
     */
    fun setPrompt(prompt: String): AudioTranscriptions

    /**
     * Sets the format of the transcript output.
     * @param format The format of the transcript output, in one of these options:
     * json, text, srt, verbose_json, or vtt.
     * @return The updated [AudioTranscriptions] object with the new response format.
     */
    fun setResponseFormat(format: String): AudioTranscriptions

    /**
     * Sets the sampling temperature, between 0 and 1. Higher values like 0.8 will make the output
     * more random, while lower values like 0.2 will make it more focused and deterministic.
     * If set to 0, the model will use log probability to automatically increase the temperature
     * until certain thresholds are hit.
     * @param temperature The sampling temperature to use.
     * @return The updated [AudioTranscriptions] object with the new temperature.
     */
    fun setTemperature(temperature: Double): AudioTranscriptions

    /**
     * Sets the language of the input audio. Supplying the input language in ISO-639-1 format will
     * improve accuracy and latency.
     * @param language The language of the input audio.
     * @return The updated [AudioTranscriptions] object with the new language.
     */
    fun setLanguage(language: String): AudioTranscriptions

    /**
     * Transcribes the given audio file into text.
     * @param filename The name of the audio file.
     * @param audioFile The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @return The transcript output in the specified format.
     * @throws CancellationException if the operation is cancelled.
     * @throws ChatGptException if there is an error transcribing the audio file.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(filename: String, audioFile: FileBytes): String

    /**
     * Transcribes the given audio file into text passes it to the provided [callback].
     * @param filename The name of the audio file.
     * @param audioFile The audio file to transcribe, in one of these formats:
     * mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @param callback The callback to receive the transcript output in the specified format.
     */
    fun execute(filename: String, audioFile: FileBytes, callback: YChat.Callback<String>)
}
