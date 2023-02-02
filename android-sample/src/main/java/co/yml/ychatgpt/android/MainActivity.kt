package co.yml.ychatgpt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.android.ui.main.MainScreen
import co.yml.ychatgpt.android.ui.presentation.PresentationScreen
import co.yml.ychatgpt.android.ui.splash.SplashScreen
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {

    private val chatGpt by lazy { ChatGpt.create(BuildConfig.API_KEY) }

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
fun Navigation(chatGpt: ChatGpt, myCoroutineContext: CoroutineContext) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {

        composable("splash_screen") {
            SplashScreen(navController)
        }

        composable("presentation_screen") {
            PresentationScreen(navController)
        }

        composable("main_screen") {
            MainScreen(chatGpt, navController)
        }

    }
}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(bottom = 18.dp, top = 18.dp)
    ) {
        Icon(imageVector = Icons.Default.Home, "", modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Settings, "", modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Menu, "", modifier = Modifier.weight(1f))
    }
}