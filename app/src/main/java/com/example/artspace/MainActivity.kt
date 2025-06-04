package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ImageTextButtons(step: Int){

    var artNumber by remember { mutableStateOf(step) }

    val imageResource = when(artNumber) {
        1 -> R.drawable.hong1
        2 -> R.drawable.hong2
        3 -> R.drawable.naz1
        4 -> R.drawable.naz2
        5 -> R.drawable.kwan1
        else -> R.drawable.kwan2
    }
    val imageDescription = when(artNumber) {
        1 -> R.string.hong1_content_description
        2 -> R.string.hong2_content_description
        3 -> R.string.naz1_content_description
        4 -> R.string.naz2_content_description
        5 -> R.string.kwan1_content_description
        else -> R.string.kwan2_content_description
    }
    val imageTitle = when(artNumber) {
        1 -> R.string.hong1_title
        2 -> R.string.hong2_title
        3 -> R.string.naz1_title
        4 -> R.string.naz2_title
        5 -> R.string.kwan1_title
        else -> R.string.kwan2_title
    }
    val authorResource = when(artNumber) {
        1 -> R.string.hong1_author
        2 -> R.string.hong2_author
        3 -> R.string.naz1_author
        4 -> R.string.naz2_author
        5 -> R.string.kwan1_author
        else -> R.string.kwan2_author
    }
    val yearResource = when(artNumber) {
        1 -> R.string.hong1_year
        2 -> R.string.hong2_year
        3 -> R.string.naz1_year
        4 -> R.string.naz2_year
        5 -> R.string.kwan1_year
        else -> R.string.kwan2_year
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                //.fillMaxWidth(0.85f)
                .width(320.dp).height(400.dp)
                .background(Color.White) // Rounded background use: shape = RoundedCornerShape(10.dp)
                .border(2.dp, Color.LightGray) // Rounded border
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageDescription.toString(),
                modifier = Modifier.width(250.dp) // Set desired size
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Box(// Text box for title and author
            modifier = Modifier
                //.fillMaxWidth(0.85f)
                .width(320.dp)
                .background(Color(red = 243, green = 242, blue = 240, alpha = 255))) {
            Column {
                Text( // Title of image
                    text = stringResource(imageTitle),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Text( // Author of image
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(authorResource))
                        }
                        append(" (") // A space without specific style
                        append(stringResource(yearResource))
                        append(")")
                    },
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        if (artNumber != 1) {
                            artNumber--
                        } else artNumber = 6
                    },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = "Previous")
                }
                // Spacer with weight: This pushes the next item to the right
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        if (artNumber != 6) {
                            artNumber++
                        } else artNumber = 1
                    },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        //color = MaterialTheme.colorScheme.background
        color = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ImageTextButtons(1)
        }
    }
}