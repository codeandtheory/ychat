package co.yml.ychatgpt.android.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.android.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(chatGpt: ChatGpt, navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var chatGptAnswer by remember {
        mutableStateOf("")
    }
    val items = remember {
        mutableStateListOf<MessageItem>()
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationItemClick = {
                    navController.navigateUp()
                }
            )
        },
        drawerContent = {
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChatLayout(items)
            }
        },
        bottomBar = {
            val typing = MessageItem(message = stringResource(R.string.typing), isOut = false)
            SendMessageLayout(onSendMessage = {
                scope.launch {
                    items.add(MessageItem(message = it, isOut = true))
                    delay(300)
                    items.add(typing)
                    chatGptAnswer = chatGpt.completion(it)
                    items.remove(typing)
                    items.add(MessageItem(message = chatGptAnswer, isOut = false))
                }
            })
        },
    )
}