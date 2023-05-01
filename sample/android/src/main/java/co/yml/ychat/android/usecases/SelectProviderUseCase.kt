package co.yml.ychat.android.usecases

import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.data.ProviderRepository

class SelectProviderUseCase(
    private val repository: ProviderRepository
) {

    suspend operator fun invoke(input: ProviderKey) {
        repository.saveProvider(input)
    }

}