package co.yml.ychatgpt.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.android.ui.MainScreen
import co.yml.ychatgpt.android.ui.SplashScreen
import kotlin.coroutines.CoroutineContext

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

        composable("main_screen") {
            MainScreen(chatGpt)
        }
    }
}