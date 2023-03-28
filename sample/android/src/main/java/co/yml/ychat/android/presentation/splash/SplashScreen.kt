package co.yml.ychat.android.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.ychat.android.router.MainRouter
import co.yml.ychat.android.ui.components.logo.LogoSplash
import co.yml.ychat.android.ui.theme.YChatTheme
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 2000L

@Composable
internal fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LogoSplash()
    }
    LaunchedEffect(key1 = true) {
        delay(SPLASH_DELAY)
        navController.navigate(MainRouter.HOME.name)
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    val navController = rememberNavController()
    YChatTheme {
        SplashScreen(navController)
    }
}
