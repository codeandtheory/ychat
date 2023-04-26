package co.yml.ychat.android.usecases

import co.yml.ychat.android.data.ProviderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn

class GetSelectedProviderKeyUseCase(
    private val repository: ProviderRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke() = repository.providerKeyFlow.flowOn(dispatcher)

}