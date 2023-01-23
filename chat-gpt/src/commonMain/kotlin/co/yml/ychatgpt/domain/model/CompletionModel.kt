package co.yml.ychatgpt.domain.model

internal data class CompletionModel(
    val id: String,
    val model: String,
    val choices: List<ChoiceModel>,
    val usage: UsageModel,
)
