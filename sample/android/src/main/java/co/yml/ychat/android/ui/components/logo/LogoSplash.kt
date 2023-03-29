package co.yml.ychat.android.ui.components.logo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun LogoSplash() {
    Image(painter = painterResource(id = R.drawable.logo_splash), contentDescription = null)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LogoSplashPreview() {
    YChatTheme {
        Box(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.BG)
        ) {
            LogoSplash()
        }
    }
}
