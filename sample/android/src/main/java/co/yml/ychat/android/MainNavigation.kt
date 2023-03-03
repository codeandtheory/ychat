package co.yml.ychat.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ychat.android.ui.MainScreen
import co.yml.ychat.android.ui.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {

        composable("splash_screen") {
            SplashScreen(navController)
        }

        composable("main_screen") {
            MainScreen()
        }
    }
}