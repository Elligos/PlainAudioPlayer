package com.example.plainaudioplayer.ui.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plainaudioplayer.R
import com.example.plainaudioplayer.ui.components.InfiniteRunningText
import com.example.plainaudioplayer.ui.theme.Dimensions
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import kotlinx.coroutines.flow.MutableStateFlow


@ExperimentalAnimationApi
@Composable
fun AudioControlBottomBar (
    playState: MutableStateFlow<Boolean>,
    artist: String,
    track: String,
    trackDuration: String,
    trackPlayedTime: String,
    trackProgress: MutableStateFlow<Float>,
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit,
    onNextClick: () -> Unit
){

    //val progress by remember { mutableStateOf(trackProgress) }
    //var playing by remember { mutableStateOf(playState) }
    val playing by playState.collectAsState()
    val progress by trackProgress.collectAsState()

    Card(
        modifier = modifier
//            .padding(Dimensions.padding_mini)
//            .border(
//                Dimensions.borderSmall,
//                MaterialTheme.colors.primary,
//                RoundedCornerShape(Dimensions.roundedSmall)
//            )
        ,
//        shape = RoundedCornerShape(Dimensions.roundedSmall),
        backgroundColor = MaterialTheme.colors.surface
        ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
//            val progress by remember { mutableStateOf(trackProgress) }
//            val playing by remember { mutableStateOf(playState) }


            Column(modifier = modifier){
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier)
                {
                    IconButton(onClick = {
                        onPlayClick()
                        //playing = playState
                    } ) {
                        Icon(
                            painter =
                                if(playing)  painterResource(id = R.drawable.baseline_pause_24)
                                else  painterResource(id = R.drawable.baseline_play_arrow_24),
                            contentDescription =
                                if(playing) "Pause icon"
                                else "Play icon",
                            modifier = modifier
                                .size(Dimensions.control_icon_width, Dimensions.control_icon_height),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_music_note_24),//TODO: modify to use image from outside as parameter
                        contentDescription = "Track icon or default music icon",
                        modifier = modifier
                            .size(Dimensions.music_icon_width, Dimensions.music_icon_height)
                            .border(  Dimensions.borderSmall,
                                      MaterialTheme.colors.primary,
                                      RoundedCornerShape(Dimensions.roundedSmall) )
                        ,
                        tint = MaterialTheme.colors.secondary
                    )
                    Column( modifier = modifier
                                        .weight(1f)
                                        .padding(start = Dimensions.padding_small),
                            horizontalAlignment = Alignment.Start)
                    {
                        //TODO: measure text overflow according to the parent width
                        if(artist.length < 30)   Text(  text = artist,
                                                        color = MaterialTheme.colors.onSurface,
                                                        maxLines = 1,
                                                        style = MaterialTheme.typography.caption)
                        else  InfiniteRunningText(  text = artist,
                                                    textStyle = MaterialTheme.typography.caption,
                                                    running = playing )

                        if(track.length < 30)   Text(  text = track,
                                                        color = MaterialTheme.colors.onSurface,
                                                        maxLines = 1,
                                                        style = MaterialTheme.typography.body1)
                        else InfiniteRunningText(   text = track,
                                                    textStyle  = MaterialTheme.typography.body1,
                                                    running = playing )

                    }

                    IconButton(onClick = onNextClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_skip_next_24),
                            contentDescription = "Skip next",
                            modifier = modifier
                                .size(Dimensions.control_icon_width, Dimensions.control_icon_height),
                            tint = MaterialTheme.colors.primary
                        )
                    }


                }
                //Divider()

                    Slider(value = progress,
                        onValueChange = { /*progress = it*/ },
                        modifier = modifier
                            .padding(8.dp, 0.dp)
                            .height(20.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colors.primary,
                            activeTrackColor = MaterialTheme.colors.primary
                        )
                    )
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, bottom = 2.dp), horizontalArrangement = Arrangement.End){
                        Text(   text = trackPlayedTime,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            style = MaterialTheme.typography.caption)// item composable
                        Text(   text = "    ",
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            style = MaterialTheme.typography.caption)// item composable
                        Text(   text = trackDuration,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            style = MaterialTheme.typography.caption)// item composable
                    }




            }

        }
    }

}

@ExperimentalAnimationApi
@Composable
@Preview
fun AudioControlBottomBarPreview()
{
    //var playState = false
    val playState = MutableStateFlow(false)
    val progress = MutableStateFlow(0.5f)
    Column() {
        PlainAudioPlayerTheme(darkTheme = true) {
            AudioControlBottomBar(
                playState = playState,
                artist = "In Flames",
                track = "State Of Slow Decay",
                trackDuration = "3:59",
                trackPlayedTime = "1:59",
                trackProgress = progress,
                modifier = Modifier,
                onPlayClick = {playState.value = !playState.value},
                onNextClick = {}
            )
        }
        PlainAudioPlayerTheme(darkTheme = false) {
            AudioControlBottomBar(
                playState = playState,
                artist = "In Flames",
                track = "State Of Slow Decay",
                trackDuration = "3:59",
                trackPlayedTime = "1:59",
                trackProgress = progress,
                modifier = Modifier,
                onPlayClick = {playState.value = !playState.value},
                onNextClick = {}
            )
        }
    }

}