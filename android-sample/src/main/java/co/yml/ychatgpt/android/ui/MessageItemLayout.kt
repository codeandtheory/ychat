package co.yml.ychatgpt.android.ui

import android.widget.Space
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychatgpt.android.R

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
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            if (isOut.not()) {
                Image(
                    painterResource(R.drawable.ic_robot),
                    contentDescription = "",
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = CircleShape)
                        .background(Color(0xFF19C37D))
                        .padding(8.dp),
                )
                Spacer(modifier = Modifier.padding(4.dp), )
            }
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomEnd = if (isOut) 0.dp else 16.dp,
                            bottomStart = if (isOut) 16.dp else 0.dp
                        )
                    )
                    .background(if (isOut) Color(0xFF448AFF) else Color(0xFFE0E0E0))
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(text = messageText, color = if (isOut) Color.White else Color(0xFF212121))
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageItemLayout() {
    MessageItemLayout(messageText = "Test", isOut = false)
}