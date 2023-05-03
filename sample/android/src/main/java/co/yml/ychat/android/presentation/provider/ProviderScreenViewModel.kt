package co.yml.ychat.android.presentation.provider

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.usecases.GetSelectedProviderKeyUseCase
import co.yml.ychat.android.usecases.SelectProviderUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class ProviderScreenViewModel(
    private val getSelectedProvider: GetSelectedProviderKeyUseCase,
    private val selectProviderUseCase: SelectProviderUseCase,
) : ViewModel() {

    val providers = mutableStateOf<List<ProviderUI>>(emptyList())

    init {
        fetchProviders()
    }

    private fun fetchProviders() = viewModelScope.launch {
        getSelectedProvider().map { selectedKey ->
            ProviderKey.values().map {
                ProviderUI(
                    name = it.name,
                    providerKey = it,
                    isSelected = it == selectedKey
                )
            }
        }
            .collect {
                providers.value = it
            }
    }

    fun selectProvider(providerKey: ProviderKey) {
        viewModelScope.launch {
            selectProviderUseCase(providerKey)
        }
    }

    data class ProviderUI(
        val name: String,
        val isSelected: Boolean = false,
        val providerKey: ProviderKey
    )

}
