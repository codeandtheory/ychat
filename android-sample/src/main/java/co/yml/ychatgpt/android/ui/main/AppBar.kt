package co.yml.ychatgpt.android.ui.main

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.yml.ychatgpt.android.R
import co.yml.ychatgpt.android.common.Dimensions.default

@Composable
fun AppBar(
    onNavigationItemClick: () -> Unit
) {
    TopAppBar(
        elevation = default,
        title = { Text(text = stringResource(R.string.health_chat)) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = onNavigationItemClick) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = stringResource(R.string.back),
                )
            }
        }
    )
}