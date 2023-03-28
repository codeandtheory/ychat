package co.yml.ychat.android.ui.components.toolbar

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun Toolbar(
    title: String,
    startIcon: Icons,
    onStartIconClick: () -> Unit = {},
) {
    TopAppBar(
        elevation = 0.dp,
        title = { TypographyStyle.DisplayTitle.Text(text = title) },
        backgroundColor = YChatTheme.colors.background,
        navigationIcon = {
            startIcon.IconButton(onClick = onStartIconClick)
        }
    )
}

@Preview
@Composable
private fun ToolbarPreview() {
    YChatTheme {
        Toolbar(
            title = "Completion",
            startIcon = Icons.Menu
        )
    }
}
