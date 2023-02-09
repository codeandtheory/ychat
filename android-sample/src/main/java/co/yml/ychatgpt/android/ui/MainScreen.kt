package co.yml.ychatgpt.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.yml.ychatgpt.android.MainViewModel
import co.yml.ychatgpt.android.MenuItem
import co.yml.ychatgpt.android.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel = koinViewModel<MainViewModel>()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationItemClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(
                MenuItem(
                    id = "completion",
                    title = stringResource(R.string.completion),
                    contentDescription = "completion",
                    icon = Icons.Default.Edit
                ),
                MenuItem(
                    id = "edits",
                    title = stringResource(R.string.edits),
                    contentDescription = "edit",
                    icon = Icons.Default.Edit
                )

            ), onItemClick = {
                // do nothing
            })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChatLayout(viewModel.items)
            }
        },
        bottomBar = {
            val typingString = stringResource(id = R.string.typing)
            val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
            var counter by remember { mutableStateOf(0) }

            // typing effect
            LaunchedEffect(key1 = counter, key2 = isLoading) {
                if (isLoading) {
                    delay(300)
                    counter += 1
                    viewModel.updateTyping(typingString)
                }
            }

            SendMessageLayout()
        },
    )
}