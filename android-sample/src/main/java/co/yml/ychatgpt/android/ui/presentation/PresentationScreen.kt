package co.yml.ychatgpt.android.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import co.yml.ychatgpt.android.BottomBar
import co.yml.ychatgpt.android.R
import co.yml.ychatgpt.android.common.Dimensions
import co.yml.ychatgpt.android.common.Dimensions.spaceExtraLarge
import co.yml.ychatgpt.android.common.Dimensions.spaceLarge
import co.yml.ychatgpt.android.common.Dimensions.spaceMedium
import co.yml.ychatgpt.android.common.Dimensions.spaceSmallPlus

@Composable
fun PresentationScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        },
        drawerContent = {
        },
        content = { padding ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Image(
                        painterResource(R.drawable.image_2),
                        contentDescription = stringResource(R.string.logo),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(
                            start = spaceMedium,
                            end = spaceMedium,
                            bottom = spaceExtraLarge
                        ),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bubble_icon),
                        contentDescription = "",
                        tint = Color.White
                    )
                    Spacer(
                        modifier = Modifier.padding(Dimensions.spaceExtraSmall)
                    )
                    Text(
                        text = stringResource(id = R.string.need_guidance),
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight(700)
                    )
                    Spacer(modifier = Modifier.padding(Dimensions.spaceExtraSmall))
                    Text(
                        text = stringResource(id = R.string.get_meal_plans),
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(400)
                    )
                    Spacer(modifier = Modifier.padding(spaceMedium))
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(size = spaceLarge)))
                            .clickable { navController.navigate("main_screen") }
                    ) {
                        Text(
                            text = stringResource(id = R.string.ask_a_question),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            modifier = Modifier
                                .background(color = colorResource(id = R.color.softGreen))
                                .padding(horizontal = spaceMedium, vertical = spaceSmallPlus)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomBar()
        },
    )
}