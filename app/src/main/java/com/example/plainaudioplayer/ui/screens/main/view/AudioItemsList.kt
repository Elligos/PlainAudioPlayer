package com.example.plainaudioplayer.ui.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.plainaudioplayer.data.AudioDetail
import com.example.plainaudioplayer.ui.components.RunningTextSpeed
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalAnimationApi
@Composable
fun AudioItemsList(audioItemsList : List<AudioDetail>)
{
        val lazyListState = rememberLazyListState()
        LazyColumn(
                state = lazyListState,
                userScrollEnabled = true
        ){
                items(audioItemsList){
                        audioItem -> AudioItem(
                        playState = MutableStateFlow(audioItemsList.indexOf(audioItem) == 2),
                        artist = audioItem.artist,
                        track = audioItem.track,
                        trackRemainingTime = "${if(audioItem.trackDuration_s/60<10) 0 else ""}${audioItem.trackDuration_s/60}:${if(audioItem.trackDuration_s%60<10) 0 else ""}${audioItem.trackDuration_s%60}",
                        trackProgress = MutableStateFlow(0f)
                ){}
                }
        }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun AudioItemsListPreview() {
        val audioList : List<AudioDetail> = listOf(
                AudioDetail("InFlames", "Wallflower", 301 ),
                AudioDetail("InFlames", "I'm Above", 196 ),
                AudioDetail("InFlames", "State Of Slow Decay", 238 ),
                AudioDetail("InFlames", "Voices", 256 ),
        )
        PlainAudioPlayerTheme(darkTheme = true) {
                AudioItemsList(audioList)
        }
        PlainAudioPlayerTheme(darkTheme = false) {
                AudioItemsList(audioList)
        }
}