package co.yml.ychat.android.presentation.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.yml.ychat.android.presentation.home.enums.HomeMenu
import co.yml.ychat.android.ui.components.sidemenu.model.MenuItem

internal class HomeViewModel : ViewModel() {

    val selectedMenuItem = mutableStateOf(HomeMenu.MODELS)

    fun setSelectedMenuItem(menuItem: MenuItem) {
        selectedMenuItem.value = HomeMenu.findById(menuItem.id)
    }
}
