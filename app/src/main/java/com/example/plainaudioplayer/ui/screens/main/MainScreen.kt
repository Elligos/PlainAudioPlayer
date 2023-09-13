package com.example.plainaudioplayer.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.plainaudioplayer.data.AudioDetail
import com.example.plainaudioplayer.ui.composables.AudioPlayController
import com.example.plainaudioplayer.ui.composables.Notification
import com.example.plainaudioplayer.ui.screens.main.AudioControlBottomBar
import com.example.plainaudioplayer.ui.screens.main.AudioItemsList
import com.example.plainaudioplayer.ui.screens.main.SearchTopBar
import com.example.plainaudioplayer.ui.theme.Dimensions
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalAnimationApi
@Preview
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {



//        Notification(
////            messageModifier = Modifier.padding(dimensionResource(id = R.dimen.padding_mini)),
////            buttonModifier = Modifier.padding(dimensionResource(id = R.dimen.padding_mini)),
//            messageModifier = Modifier.padding(Dimensions.padding_mini),
//            buttonModifier = Modifier.padding(Dimensions.padding_mini),
//            textNotification = "Notification",
//            clickNotification = {
//                Log.i("TAG#", "NotificationPreview: clickNotification")
//            }
//        )
        //var playState = false
        val playState = MutableStateFlow(false)
        val progress = MutableStateFlow(0.5f)
        val audioList : List<AudioDetail> = listOf(
            AudioDetail("InFlames", "Wallflower", 301 ),
            AudioDetail("InFlames", "I'm Above", 196 ),
            AudioDetail("InFlames", "State Of Slow Decay", 119 ),
            AudioDetail("InFlames", "Voices", 256 ),
            AudioDetail("InFlames", "Voices Voices Voices Voices Voices Voices Voices", 256 ),
            AudioDetail("InFlames", "Voices", 256 ),
            AudioDetail("InFlames", "Voices", 256 ),
            AudioDetail("InFlames", "Voices", 256 ),
            AudioDetail("InFlames", "Voices", 256 ),
            AudioDetail("InFlames", "Voices", 256 ),
        )
        PlainAudioPlayerTheme(darkTheme = true) {
//            AudioPlayController()
//            Column() {
//                SearchTopBar(navigateUp = { }, onQueryChange = {} , searchHint = "search hint"){}
//                AudioControlBottomBar(
//                    playState = playState,
//                    artist = "In Flames",
//                    track = "State Of Slow Decay",
//                    trackDuration = "3:59",
//                    trackPlayedTime = "1:59",
//                    trackProgress = progress,
//                    modifier = Modifier,
//                    onPlayClick = {
//                        playState.value = !playState.value
//                        Log.i("AudioControlBottomBar", "Play icon clicked! playState = $playState")},
//                    onNextClick = {})
//            }
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                // attach snackbar host state to the scaffold
                scaffoldState = scaffoldState,
                // Custom AppBar contains fake search bar.
                topBar = {
                    SearchTopBar(navigateUp = {}, onQueryChange = {} , searchHint = "search hint"){}
                },
                bottomBar = {
                    AudioControlBottomBar(
                        playState = playState,
                        artist = "In Flames",
                        track = "State Of Slow Decay",
                        trackDuration = "3:59",
                        trackPlayedTime = "1:59",
                        trackProgress = progress,
                        modifier = Modifier,
                        onPlayClick = {
                            playState.value = !playState.value
                            Log.i("AudioControlBottomBar", "Play icon clicked! playState = $playState")},
                        onNextClick = {})
                },
                backgroundColor = MaterialTheme.colors.background
                // Host for custom snackbar
                //snackbarHost = { HomeSnackbar(it) }
            ){paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues = paddingValues)
                ){
                    AudioItemsList(audioList)
                }

            }

        }

//        Accessibility(
//            onClickText = {
//                Log.i("TAG#", "AccessibilityPreview: onClickText")
//            },
//            onClickButton = {
//                Log.i("TAG#", "AccessibilityPreview: onClickButton")
//            },
//            onClickRadio = {
//                Log.i("TAG#", "AccessibilityPreview: onClickRadio = $it")
//            }
//        )
    }
}