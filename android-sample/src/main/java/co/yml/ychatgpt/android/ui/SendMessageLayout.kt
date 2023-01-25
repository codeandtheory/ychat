package co.yml.ychatgpt.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychatgpt.android.R
import kotlinx.coroutines.launch

@Composable
fun SendMessageLayout(
    onSendMessage: (String) -> Unit
) {
    var textFieldState by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFFE0E0E0),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            ),
            value = textFieldState,
            label = { Text(text = stringResource(R.string.message)) },
            onValueChange = { textFieldState = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .size(56.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(Color(0xFF448AFF)),
                onClick = {
                    scope.launch {
                        onSendMessage(textFieldState)
                        textFieldState = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send, stringResource(R.string.send), tint = MaterialTheme.colors.background
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SendMessageLayout(onSendMessage = {})
}