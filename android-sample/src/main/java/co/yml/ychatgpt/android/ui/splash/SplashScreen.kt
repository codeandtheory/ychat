package co.yml.ychatgpt.android.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import co.yml.ychatgpt.android.R
import co.yml.ychatgpt.android.common.Dimensions.padding90
import co.yml.ychatgpt.android.common.Dimensions.spaceExtraLarge
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate("presentation_screen")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.image_1259),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(padding90)
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spaceExtraLarge)
            )
            {
                Row() {
                    Text(
                        text = stringResource(R.string.health),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.lightBlue)
                    )
                    Text(
                        text = stringResource(R.string.chatgpt),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(300),
                        color = colorResource(id = R.color.lightBlue)
                    )
                }
            }
        }
    }
}