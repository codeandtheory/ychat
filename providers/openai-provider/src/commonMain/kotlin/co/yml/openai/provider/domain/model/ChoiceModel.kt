package co.yml.openai.provider.domain.model

internal data class ChoiceModel(
    val text: String,
    val index: Int,
    val logProbs: Int?,
    val finishReason: String,
)
