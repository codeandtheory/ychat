package co.yml.ychatgpt.domain.model

internal data class UsageModel(
    val promptToken: Int,
    val completionTokens: Int,
    val totalTokens: Int,
)
