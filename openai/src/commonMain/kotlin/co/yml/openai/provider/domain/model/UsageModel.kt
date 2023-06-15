package co.yml.openai.provider.domain.model

internal data class UsageModel(
    val promptToken: Int,
    val completionTokens: Int,
    val totalTokens: Int,
)
