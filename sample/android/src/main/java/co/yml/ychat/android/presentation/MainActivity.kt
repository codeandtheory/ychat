package co.yml.ychat.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ychat.android.presentation.home.screen.HomeScreen
import co.yml.ychat.android.presentation.splash.SplashScreen
import co.yml.ychat.android.router.MainRouter
import co.yml.ychat.android.ui.theme.YChatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { YChatTheme { Navigation() } }
    }

    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainRouter.SPLASH.name
        ) {
            composable(MainRouter.SPLASH.name) {
                SplashScreen(navController)
            }
            composable(MainRouter.HOME.name) {
                HomeScreen()
            }
        }
    }
}
