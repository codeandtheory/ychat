package co.yml.ychatgpt.android.ui.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychatgpt.android.R
import co.yml.ychatgpt.android.common.Dimensions.default
import co.yml.ychatgpt.android.common.Dimensions.robotMessageIconSize
import co.yml.ychatgpt.android.common.Dimensions.robotMessagePaddingSize
import co.yml.ychatgpt.android.common.Dimensions.spaceExtraSmall
import co.yml.ychatgpt.android.common.Dimensions.spaceMedium
import co.yml.ychatgpt.android.common.Dimensions.spaceSmall

@Composable
fun MessageItemLayout(
    messageText: String,
    isOut: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(top = spaceMedium),
            verticalAlignment = Alignment.Bottom
        ) {
            if (isOut.not()) {
                Image(
                    painterResource(R.drawable.ic_robot),
                    contentDescription = "",
                    modifier = Modifier
                        .width(robotMessageIconSize)
                        .height(robotMessageIconSize)
                        .clip(shape = CircleShape)
                        .background(colorResource(id = R.color.softGreen))
                        .padding(robotMessagePaddingSize),
                )
                Spacer(modifier = Modifier.padding(spaceExtraSmall))
            }
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = spaceMedium,
                            topEnd = spaceMedium,
                            bottomEnd = if (isOut) default else spaceMedium,
                            bottomStart = if (isOut) spaceMedium else default
                        )
                    )
                    .background(if (isOut) colorResource(id = R.color.darkGreen) else colorResource(id = R.color.opaqueWhite))
                    .padding(top = spaceSmall, bottom = spaceSmall, start = spaceMedium, end = spaceMedium)
            ) {
                Text(text = messageText, color = if (isOut) colorResource(id = R.color.white) else colorResource(id = R.color.softBlack))
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewMessageItemLayout() {
    MessageItemLayout(messageText = "Message", isOut = false)
}