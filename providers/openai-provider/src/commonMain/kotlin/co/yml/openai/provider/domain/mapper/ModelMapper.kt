package co.yml.openai.provider.domain.mapper

import co.yml.openai.provider.data.dto.ModelDto
import co.yml.openai.provider.data.dto.ModelPermissionDto
import co.yml.openai.provider.domain.model.AIModel
import co.yml.openai.provider.domain.model.AIModelPermission

internal fun List<ModelDto>.toModel(): List<AIModel> {
    return this.map { it.toModel() }
}

internal fun ModelDto.toModel(): AIModel {
    return AIModel(
        id = this.id,
        ownedBy = this.ownedBy,
        permission = this.permission.map { it.toPermission() }
    )
}

internal fun ModelPermissionDto.toPermission(): AIModelPermission {
    return AIModelPermission(
        id = id,
        allowCreateEngine = allowCreateEngine,
        allowSampling = allowSampling,
        allowLogProbs = allowLogProbs,
        allowSearchIndices = allowSearchIndices,
        allowView = allowView,
        allowFineTuning = allowFineTuning,
        organization = organization,
        isBlocking = isBlocking,
    )
}
