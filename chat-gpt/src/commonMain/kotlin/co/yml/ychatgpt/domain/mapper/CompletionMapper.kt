package co.yml.ychatgpt.domain.mapper

import co.yml.ychatgpt.data.dto.CompletionDto
import co.yml.ychatgpt.data.dto.CompletionParamsDto
import co.yml.ychatgpt.domain.model.ChoiceModel
import co.yml.ychatgpt.domain.model.CompletionModel
import co.yml.ychatgpt.domain.model.CompletionParams
import co.yml.ychatgpt.domain.model.UsageModel

internal fun CompletionDto.toCompletionModel(): CompletionModel {
    return CompletionModel(
        id = this.id,
        model = this.model,
        choices = this.choices.map {
            ChoiceModel(
                text = it.text,
                index = it.index,
                logProbs = it.logProbs,
                finishReason = it.finishReason
            )
        },
        usage = UsageModel(
            promptToken = this.usage.promptToken,
            completionTokens = this.usage.completionTokens,
            totalTokens = this.usage.totalTokens
        )
    )
}

internal fun CompletionParams.toCompletionParamsDto(): CompletionParamsDto {
    return CompletionParamsDto(
        model = this.model,
        prompt = this.prompt,
        maxTokens = this.maxTokens,
        temperature = this.temperature,
        topP = this.topP,
    )
}
