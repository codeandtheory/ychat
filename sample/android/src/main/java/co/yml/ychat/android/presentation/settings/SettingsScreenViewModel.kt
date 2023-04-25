package co.yml.ychat.android.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.usecases.GetSelectedProviderKeyUseCase
import co.yml.ychat.android.usecases.SelectProviderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class SettingsScreenViewModel(
    val getSelectedProvider: GetSelectedProviderKeyUseCase,
    val selectProviderUseCase: SelectProviderUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)

    val state: StateFlow<State> = _state.asStateFlow()

    init {
        fetchProviders()
    }

    fun fetchProviders() = viewModelScope.launch {
        getSelectedProvider().map { selectedKey ->
            ProviderKey.values().map {
                ProviderUI(
                    name = it.name,
                    providerKey = selectedKey,
                    isSelected = it == selectedKey
                )
            }
        }
            .collect {
                _state.value = State.Success(it)
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

    sealed class State {
        object Loading : State()
        data class Success(val providerKey: List<ProviderUI>) : State()
        object Error : State()
    }

}
