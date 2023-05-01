package co.yml.ychat.android.presentation.home.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import co.yml.ychat.android.presentation.audio.AudioScreen
import co.yml.ychat.android.presentation.chatcompletions.screen.ChatCompletionsScreen
import co.yml.ychat.android.presentation.completions.CompletionsScreen
import co.yml.ychat.android.presentation.edits.EditsScreen
import co.yml.ychat.android.presentation.home.enums.HomeMenu
import co.yml.ychat.android.presentation.home.viewmodel.HomeViewModel
import co.yml.ychat.android.presentation.images.ImagesScreen
import co.yml.ychat.android.presentation.models.ModelsScreen
import co.yml.ychat.android.presentation.settings.SettingsScreen
import co.yml.ychat.android.ui.components.sidemenu.SideMenu
import co.yml.ychat.android.ui.components.toolbar.Toolbar
import co.yml.ychat.android.ui.theme.Icons
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
internal fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar(scaffoldState, viewModel) },
        drawerContent = { HomeScreenSideMenu(scaffoldState, viewModel) },
        content = {
            when (viewModel.selectedMenuItem.value.id) {
                HomeMenu.MODELS.id -> ModelsScreen()
                HomeMenu.COMPLETIONS.id -> CompletionsScreen()
                HomeMenu.CHAT_COMPLETIONS.id -> ChatCompletionsScreen()
                HomeMenu.EDITS.id -> EditsScreen()
                HomeMenu.IMAGES.id -> ImagesScreen()
                HomeMenu.AUDIO.id -> AudioScreen()
                HomeMenu.SETTINGS.id -> SettingsScreen()
            }
        }
    )
}

@Composable
private fun HomeScreenTopBar(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel,
) {
    val scope = rememberCoroutineScope()
    Toolbar(
        title = viewModel.selectedMenuItem.value.titleString,
        startIcon = Icons.Menu,
        onStartIconClick = { scope.launch { scaffoldState.drawerState.open() } }
    )
}

@Composable
private fun HomeScreenSideMenu(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel,
) {
    val scope = rememberCoroutineScope()
    val items = viewModel.menuItems.value.map { it.getMenuItem() }
    val selectedItem = viewModel.selectedMenuItem.value.getMenuItem()
    SideMenu(items = items, selectedItem = selectedItem) { clickedItem ->
        viewModel.setSelectedMenuItem(clickedItem)
        scope.launch { scaffoldState.drawerState.close() }
    }
}

//@Preview
//@Composable
//private fun HomeScreenPreview() {
//    YChatTheme(true) {
//        HomeScreen(HomeViewModel(
//            GetSelectedProviderKeyUseCase()
//        ))
//    }
//}
