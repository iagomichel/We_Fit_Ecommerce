package com.iagomichel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.iagomichel.wefittest.MainActivity
import com.iagomichel.wefittest.R
import com.iagomichel.wefittest.base.ColorBackground
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var showSplash by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(3000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_top_splash),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_bottom_splash),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.welcome),
                color = Color.White,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.we_movies),
                color = Color.White,
                fontSize = 42.sp
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}