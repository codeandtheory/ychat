package co.yml.ychat.android.ui.components.logo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun LogoLabel() {
    Image(
        painter = painterResource(id = R.drawable.logo_label),
        contentDescription = null,
        colorFilter = ColorFilter.tint(YChatTheme.colors.icon1)
    )
}

@Preview
@Composable
private fun LogoSplashPreview() {
    YChatTheme {
        Box(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.BG)
        ) {
            LogoLabel()
        }
    }
}