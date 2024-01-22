package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var artSelected by remember {
        mutableStateOf(1)
    }

    val imageResource = when(artSelected) {
        1 -> R.drawable.sight_sense_plants_collage
        2 -> R.drawable.sight_sense_plants_collage__1_
        else -> R.drawable.smelling_sense_mushrooms_collage
    }

    val titleResource = when(artSelected) {
        1 -> R.string.title_1
        2 -> R.string.title_2
        else -> R.string.title_3
    }

    fun changeArt (action: String) {
        when(action) {
            "next" -> {
                if(artSelected === 3) {
                    artSelected = 1
                    return
                }
                artSelected += 1
            }
            "previous" -> {
                if(artSelected === 1) {
                    artSelected = 3
                    return
                }
                artSelected -= 1
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier
            .weight(1f)
            .fillMaxHeight())
        ArtExhibition(imageResource = imageResource, titleResource = titleResource)
        Spacer(modifier = Modifier
            .weight(2f)
            .fillMaxHeight())
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { changeArt("previous") }, modifier = Modifier.width(124.dp)) {
                Text(text = "Previous", fontSize = 14.sp)
            }
            Button(onClick = { changeArt("next") }, modifier = Modifier.width(124.dp)) {
                Text(text = "Next", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ArtExhibition (imageResource: Int, titleResource: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedCard (
            border = BorderStroke(16.dp, Color.LightGray),
            modifier = Modifier.size(width = 260.dp, height = 340.dp),

            ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = titleResource),
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(id = titleResource), fontSize = 24.sp, textAlign = TextAlign.Center)
            Text(text = "Artwork Artist ( Year )", fontSize = 18.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}