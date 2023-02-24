package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.domain.model.ChoiceModel
import co.yml.ychat.domain.model.CompletionModel
import co.yml.ychat.domain.model.CompletionParams
import co.yml.ychat.domain.model.UsageModel

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
