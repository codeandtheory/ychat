package co.yml.ychat.android.ui.components.output

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.components.loading.TypingTextLoading
import co.yml.ychat.android.ui.theme.Black1
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Green2
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.Red1
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
internal fun OutputBox(
    outputBoxStates: List<OutputBoxState>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(YChatTheme.colors.primary5)
            .clip(RoundedCornerShape(Dimens.XS))
            .border(1.dp, YChatTheme.colors.primary4)
            .padding(vertical = 20.dp, horizontal = Dimens.MD)
            .heightIn(min = 200.dp),
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimens.MD)) {
            items(outputBoxStates) {
                when (it) {
                    is OutputBoxState.Error -> ErrorRow()
                    is OutputBoxState.Text -> TextRow(it)
                    is OutputBoxState.Loading -> TypingTextLoading()
                }
            }
        }
    }
}

@Composable
private fun TextRow(textState: OutputBoxState.Text) {
    val backgroundColor = if (textState.isMarked) Color.Green2 else Color.Transparent
    val textColor = if (textState.isMarked) Color.Black1 else YChatTheme.colors.text1
    TypographyStyle.MediumBody.Text(
        text = textState.text,
        modifier = Modifier.background(backgroundColor),
        color = textColor,
    )
}

@Composable
private fun ErrorRow() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.XS),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icons.Warning.Icon(tint = Color.Red1, modifier = Modifier.size(Dimens.MD))
        TypographyStyle.MediumBody.Text(
            text = stringResource(id = R.string.output_box_error_message)
        )
    }
}

@Preview
@Composable
private fun ContainedButtonPreview() {
    val stateList = listOf(
        OutputBoxState.Text("Write a tagline for an ice cream shop."),
        OutputBoxState.Loading,
        OutputBoxState.Text("We serve up smiles with every scoop!", true),
        OutputBoxState.Error,
    )
    YChatTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .fillMaxSize()
        ) {
            OutputBox(stateList, Modifier.padding(Dimens.MD))
        }
    }
}
