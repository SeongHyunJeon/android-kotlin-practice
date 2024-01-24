package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArsSpaceApp(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun ArsSpaceApp(modifier: Modifier = Modifier) {
    var screenNumber by remember { mutableStateOf(0) }
    val siteImage: Int
    val siteName: Int
    val siteAddress: Int

    when(makeVaildNumber(screenNumber)) {
        0 -> {
            siteImage = R.drawable._mi5h2215g896cshta21c
            siteName = R.string.tourist_site_library
            siteAddress = R.string.tourist_site_library_address
        }
        1 -> {
            siteImage = R.drawable.p7200099
            siteName = R.string.tourist_site_cheonggye
            siteAddress = R.string.tourist_site_cheonggye_address
        }
        2 -> {
            siteImage = R.drawable.seoul_attractions_bukchon_hanok_village
            siteName = R.string.tourist_site_hanok
            siteAddress = R.string.tourist_site_hanok_address
        }
        3 -> {
            siteImage = R.drawable.seoul_attractions_namdaemun_market
            siteName = R.string.tourist_site_namdaemun
            siteAddress = R.string.tourist_site_namdaemun_address
        }
        else -> {
            siteImage = R.drawable.maxresdefault
            siteName = R.string.tourist_site_myeongdong
            siteAddress = R.string.tourist_site_myeongdong_address
        }
    }

    Column(
        modifier = modifier
            .background(color = colorResource(R.color.Card_background))
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.title_part1),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(R.string.title_part2),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.picture_height)),
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_elevation))
        ) {
            Image(
                painter = painterResource(siteImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_large))
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 28.dp)
                .width(dimensionResource(R.dimen.information_width))
                .height(dimensionResource(R.dimen.information_height))
                .alpha(0.6f)
                .background(colorResource(R.color.black))
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.information_inside_width))
                    .height(dimensionResource(R.dimen.information_inside_height))
                    .border(4.dp, colorResource(R.color.white))
            ) {
                Text(
                    text = stringResource(siteName),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                )
                Text(
                    text = stringResource(siteAddress),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = { screenNumber-- },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.button1_color)),
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.button1_padding_end))
                    .width(dimensionResource(R.dimen.button1_width_height))
                    .height(dimensionResource(R.dimen.button1_width_height))
            ) {
                Text(
                    text = stringResource(R.string.button_pre),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Button(
                onClick = { screenNumber++ },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.button2_color)),
                border = BorderStroke(16.dp, colorResource(R.color.button2_border_color)),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button2_width_height))
                    .height(dimensionResource(R.dimen.button2_width_height))
            ) {
                Text(
                    text = stringResource(R.string.button_next),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

fun makeVaildNumber(number: Int): Int {
    if(number < 0)
        return 0
    
    return number % 5
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArsSpaceApp(Modifier.fillMaxSize())
    }
}