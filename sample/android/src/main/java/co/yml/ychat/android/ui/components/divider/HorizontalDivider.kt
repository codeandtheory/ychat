package co.yml.ychat.android.ui.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(YChatTheme.colors.divider)
    )
}

@Preview
@Composable
private fun HorizontalDividerPreview() {
    YChatTheme {
        Column(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.XXXL)
        ) {
            HorizontalDivider()
        }
    }
}
