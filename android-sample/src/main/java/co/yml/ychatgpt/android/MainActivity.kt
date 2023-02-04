package co.yml.ychatgpt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ychatgpt.YChatGpt
import co.yml.ychatgpt.android.ui.AppBar
import co.yml.ychatgpt.android.ui.ChatLayout
import co.yml.ychatgpt.android.ui.Dimensions.spaceMedium
import co.yml.ychatgpt.android.ui.Dimensions.splashIconSize
import co.yml.ychatgpt.android.ui.DrawerBody
import co.yml.ychatgpt.android.ui.DrawerHeader
import co.yml.ychatgpt.android.ui.SendMessageLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {

    private val chatGpt by lazy { YChatGpt.create(BuildConfig.API_KEY) }

    private val myCoroutineContext by lazy { lifecycleScope.coroutineContext }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                Navigation(chatGpt, myCoroutineContext)
            }
        }
    }
}

@Composable
fun Navigation(chatGpt: YChatGpt, myCoroutineContext: CoroutineContext) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {

        composable("splash_screen") {
            SplashScreen(navController)
        }

        composable("main_screen") {
            MainScreen(chatGpt, myCoroutineContext)
        }
    }

}

@Composable
fun MainScreen(chatGpt: YChatGpt, myCoroutineContext: CoroutineContext) {
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
                ChatLayout(items)
            }
        },
        bottomBar = {
            SendMessageLayout(onSendMessage = {
                scope.launch {
                    items.add(MessageItem(message = it, isOut = true))
                    chatGptAnswer = chatGpt.completion()
                        .setInput(it)
                        .setMaxTokens(1024)
                        .execute()
                    items.add(MessageItem(message = chatGptAnswer, isOut = false))
                }
            })
        },
    )
}

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate("main_screen")
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.ic_chat),
                    contentDescription = stringResource(R.string.logo),
                    modifier = Modifier
                        .width(splashIconSize)
                        .height(splashIconSize)
                )
                Spacer(modifier = Modifier.width(spaceMedium))
                Text(
                    text = stringResource(R.string.ychat_gpt),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}