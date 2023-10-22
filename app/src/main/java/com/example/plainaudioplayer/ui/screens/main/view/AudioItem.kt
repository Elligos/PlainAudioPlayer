package com.example.plainaudioplayer.ui.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.plainaudioplayer.R
import com.example.plainaudioplayer.ui.components.InfiniteRunningText
import com.example.plainaudioplayer.ui.theme.Dimensions
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import kotlinx.coroutines.flow.MutableStateFlow


@ExperimentalAnimationApi
@Composable
fun AudioItem (
    playState: MutableStateFlow<Boolean>,
    artist: String,
    track: String,
    trackRemainingTime: String,
    trackProgress: MutableStateFlow<Float>,
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit,
){

    val playing by playState.collectAsState()

    Card(
        modifier = modifier
            .padding(Dimensions.padding_mini)
            .border(
                width = Dimensions.borderMini,
                color = if(playing) MaterialTheme.colors.primary
                        else MaterialTheme.colors.surface,
                shape = RoundedCornerShape(Dimensions.roundedSmall)
            ),
        shape = RoundedCornerShape(Dimensions.roundedSmall),
        backgroundColor = MaterialTheme.colors.background
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.padding_small,
                    top = Dimensions.padding_small,
                    bottom = Dimensions.padding_small)
        ){

                    IconButton(onClick = {
                        onPlayClick()
                    } ) {
                        Icon(
                            painter =
                            if(playing)  painterResource(id = R.drawable.baseline_pause_24)
                            else  painterResource(id = R.drawable.baseline_play_arrow_24),
                            contentDescription =
                            if(playing) "Pause icon"
                            else "Play icon",
                            modifier = modifier
                                .size(Dimensions.audio_item_control_icon_width,
                                    Dimensions.audio_item_control_icon_height)
                                .padding(end = Dimensions.padding_mini),
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
                                RoundedCornerShape(Dimensions.roundedMini) )
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

                    Text(   text = trackRemainingTime,
                        modifier = Modifier.padding(end = Dimensions.padding_small),
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        style = MaterialTheme.typography.caption)// item composable

        }
    }

}

@ExperimentalAnimationApi
@Composable
@Preview
fun AudioItemPreview()
{
    //var playState = false
    val playState = MutableStateFlow(true)
    val progress = MutableStateFlow(0.5f)
    Column() {
        PlainAudioPlayerTheme(darkTheme = true) {
            AudioItem(
                playState = playState,
                artist = "In Flames",
                track = "State Of Slow Decay",
                trackRemainingTime = "1:59",
                trackProgress = progress,
                modifier = Modifier,
                onPlayClick = {playState.value = !playState.value},
            )
        }
        PlainAudioPlayerTheme(darkTheme = false) {
            AudioItem(
                playState = playState,
                artist = "In Flames",
                track = "State Of Slow Decay",
                trackRemainingTime = "1:59",
                trackProgress = progress,
                modifier = Modifier,
                onPlayClick = {playState.value = !playState.value},
            )
        }
    }

}