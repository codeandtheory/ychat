package co.yml.ychat.android.ui.theme

import androidx.compose.material.Colors as MaterialColors
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object YChatTheme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
}

@Composable
fun YChatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors
    ProvideColors(colors = colors) {
        MaterialTheme(
            colors = MaterialColors(colors, isLight = !darkTheme),
            content = content
        )
    }
}

@Composable
private fun MaterialColors(colors: Colors, isLight: Boolean): MaterialColors {
    return MaterialColors(
        primary = colors.primary1,
        primaryVariant = colors.primary1,
        secondary = colors.primary2,
        secondaryVariant = colors.primary2,
        background = colors.background,
        surface = colors.onAccent,
        error = Color.Red1,
        onPrimary = colors.onAccent,
        onSecondary = colors.onAccent,
        onBackground = colors.accent,
        onSurface = colors.accent,
        onError = Color.White1,
        isLight
    )
}
