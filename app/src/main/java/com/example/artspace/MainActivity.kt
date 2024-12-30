package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceImage(
    @DrawableRes imageResource: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageResource),
        contentDescription = "cat1",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(width = 350.dp, height = 500.dp)
            .border(1.dp, color = Color.LightGray)
            .padding(32.dp)
    )
}

@Composable
fun ArtSpaceTitleText(
    @StringRes textName: Int,
    @StringRes textArtist: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth(0.9f)
    ) {
        Text(
            text = stringResource(textName),
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(textArtist),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )
    }
}

@Composable
fun ArtSpaceButton(
    buttonFunction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Button(
            colors = ButtonColors(
                containerColor = Color.Gray,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White),
            modifier = Modifier.width(148.dp),
            onClick = buttonFunction) {
            Text(
                text = "Previous"
            )
        }
        Button(
            colors = ButtonColors(
                containerColor = Color.Gray,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White),
            modifier = Modifier.width(148.dp),
            onClick = buttonFunction) {
            Text(
                text = "Next"
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun ArtSpaceApp() {
    var chooseNumber by remember { mutableStateOf(1)}

    var imageResource: Int = when(chooseNumber) {
        1 -> R.drawable.cat1
        2 -> R.drawable.cat2
        else -> R.drawable.cat3
    }
    var textNameResource: Int = when(chooseNumber) {
        1 -> R.string.cat1_name
        2 -> R.string.cat2_name
        else -> R.string.cat3_name
    }
    var textArtistResource: Int = when(chooseNumber) {
        1 -> R.string.cat1_Artist
        2 -> R.string.cat2_Artist
        else -> R.string.cat3_Artist
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ArtSpaceImage(imageResource = imageResource)
        Spacer(
            modifier = Modifier
                .padding(24.dp)
        )
        ArtSpaceTitleText(
            textName = textNameResource,
            textArtist = textArtistResource
        )
        Spacer(
            modifier = Modifier
                .padding(12.dp)
        )
        ArtSpaceButton(
            buttonFunction = {
                if (chooseNumber >= 3) {
                    chooseNumber = 1
                } else {
                    chooseNumber++
                }
            }
        )
    }
}