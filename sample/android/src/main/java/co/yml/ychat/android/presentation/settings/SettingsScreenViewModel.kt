package co.yml.ychat.android.presentation.settings

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.android.R
import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.usecases.GetSelectedProviderKeyUseCase
import co.yml.ychat.android.usecases.SelectProviderUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class SettingsScreenViewModel(
    private val getSelectedProvider: GetSelectedProviderKeyUseCase,
    private val selectProviderUseCase: SelectProviderUseCase,
) : ViewModel() {

    val providers = mutableStateOf<ProviderUI?>(null)

    init {
        fetchProviders()
    }

    private fun fetchProviders() = viewModelScope.launch {
        getSelectedProvider().map { selectedKey ->
            ProviderUI(
                providerName = selectedKey.name
            )
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
        @DrawableRes val leftIcon: Int = R.drawable.ic_api,
        @DrawableRes val rightIcon: Int = R.drawable.ic_arrow_right,
        val providerName: String
    )

}
