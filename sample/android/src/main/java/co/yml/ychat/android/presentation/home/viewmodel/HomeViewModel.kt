package co.yml.ychat.android.presentation.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.android.presentation.home.enums.HomeMenu
import co.yml.ychat.android.ui.components.sidemenu.model.MenuItem
import co.yml.ychat.android.usecases.GetSelectedProviderKeyUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class HomeViewModel(val getSelectedProviderKey: GetSelectedProviderKeyUseCase) :
    ViewModel() {

    val selectedMenuItem = mutableStateOf(HomeMenu.MODELS)

    val menuItems = mutableStateOf<List<HomeMenu>>(emptyList())

    init {
        fetchMenus()
    }

    private fun fetchMenus() = viewModelScope.launch {
        getSelectedProviderKey().map { key ->
            HomeMenu.values().filter { it.availability.contains(key) }
        }
            .collect {
                menuItems.value = it.apply {
                    if (selectedMenuItem.value != HomeMenu.SETTINGS) {
                        selectedMenuItem.value = first()
                    }
                }
            }
    }

    fun setSelectedMenuItem(menuItem: MenuItem) {
        selectedMenuItem.value = HomeMenu.findById(menuItem.id)
    }


}
