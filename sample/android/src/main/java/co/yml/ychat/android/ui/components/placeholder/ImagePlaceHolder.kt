package co.yml.ychat.android.ui.components.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun ImagePlaceHolder() {
    Box(
        modifier = Modifier
            .background(YChatTheme.colors.primary4)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icons.Image
            .Icon(
                modifier = Modifier.size(Dimens.XXXL),
                tint = YChatTheme.colors.primary3
            )
    }
}

@Preview
@Composable
private fun ImagePlaceHolderPreview() {
    YChatTheme {
        Box(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .fillMaxSize()
                .padding(Dimens.XXXL)
            ,
            contentAlignment = Alignment.Center,
        ) {
            ImagePlaceHolder()
        }
    }
}
