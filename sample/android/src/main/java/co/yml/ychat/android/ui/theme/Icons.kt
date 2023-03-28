package co.yml.ychat.android.ui.theme

import androidx.compose.material.IconButton as MaterialIconButton
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.R

sealed class Icons(@DrawableRes private val imageResource: Int) {

    object ChatBubbleOutline : Icons(R.drawable.ic_chat_bubble_outline)
    object Model : Icons(R.drawable.ic_model)
    object Text : Icons(R.drawable.ic_text)
    object Image : Icons(R.drawable.ic_image)
    object Audio : Icons(R.drawable.ic_audio)
    object Menu : Icons(R.drawable.ic_menu)
    object Send : Icons(R.drawable.ic_send)
    object Bot : Icons(R.drawable.ic_bot)
    object Warning : Icons(R.drawable.ic_warning)
    object WarningOutline : Icons(R.drawable.ic_warning_outline)
    object Settings: Icons(R.drawable.ic_settings)
    object Edit: Icons(R.drawable.ic_edit)
    object Construction: Icons(R.drawable.ic_construction)

    @Composable
    fun Icon(
        modifier: Modifier = Modifier,
        tint: Color = YChatTheme.colors.icon1,
    ) {
        Icon(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            tint = tint,
            modifier = modifier,
        )
    }

    @Composable
    fun IconButton(
        modifier: Modifier = Modifier,
        tint: Color = YChatTheme.colors.icon1,
        enabled: Boolean = true,
        onClick: () -> Unit,
    ) {
        MaterialIconButton(onClick, modifier, enabled) {
            this.Icon(tint = tint)
        }
    }
}

@Preview
@Composable
private fun IconPreview() {
    YChatTheme(true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(YChatTheme.colors.background),
        ) {
            Icons.ChatBubbleOutline.Icon()
            Icons.Model.Icon()
            Icons.Text.Icon()
            Icons.Image.Icon()
            Icons.Audio.Icon()
            Icons.Menu.Icon()
            Icons.Send.Icon()
            Icons.Bot.Icon()
            Icons.Warning.Icon()
            Icons.WarningOutline.Icon()
            Icons.Settings.Icon()
            Icons.Edit.Icon()
            Icons.Construction.Icon()
        }
    }
}
