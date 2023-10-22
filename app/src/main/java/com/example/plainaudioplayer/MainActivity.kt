package com.example.plainaudioplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plainaudioplayer.ui.screens.MainScreen
import com.example.plainaudioplayer.ui.screens.main.viewmodel.MainScreenViewModel
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainViewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainViewModel.onNewQuery("In Flames")
        setContent {
            PlainAudioPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlainAudioPlayerTheme {
        Greeting("Android")
    }
}