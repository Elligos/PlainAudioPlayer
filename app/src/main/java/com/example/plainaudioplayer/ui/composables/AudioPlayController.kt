package com.example.plainaudioplayer.ui.composables

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.plainaudioplayer.R
import com.example.plainaudioplayer.ui.theme.Dimensions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun AudioPlayController (
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(Dimensions.padding_mini)
            .border(
                Dimensions.borderSmall,
                MaterialTheme.colors.secondary,
                RoundedCornerShape(Dimensions.roundedSmall)
            ),
        shape = RoundedCornerShape(Dimensions.roundedSmall),

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            var playing = true
            var progress by remember { mutableStateOf(0.1f) }
            var playState by remember { mutableStateOf(playing) }




            Column(modifier = modifier){
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier)
                {
//                    IconButton(onClick = { playState = !playState }) {
//                        if(playState){
//                            Icon(
//                                painter = painterResource(id = R.drawable.baseline_play_circle_filled_24),
//                                contentDescription = "Play icon",
//                                modifier = modifier
//                                    .size(75.dp, 75.dp),
//                                tint = MaterialTheme.colors.secondary
//                            )
//                        }
//                        else{
//                            Icon(
//                                painter = painterResource(id = R.drawable.baseline_pause_circle_filled_24),
//                                contentDescription = "Pause icon",
//                                modifier = modifier
//                                    .size(75.dp, 75.dp),
//                                tint = MaterialTheme.colors.secondary
//                            )
//                        }
//                    }
                    IconButton(onClick = { playState = !playState }) {
                            Icon(
                                painter =
                                    if(playState)  painterResource(id = R.drawable.baseline_pause_circle_filled_24)
                                    else  painterResource(id = R.drawable.baseline_play_circle_filled_24),
                                contentDescription =
                                    if(playState) "Pause icon"
                                    else "Play icon",
                                modifier = modifier
                                    .size(75.dp, 75.dp),
                                tint = MaterialTheme.colors.secondary
                            )
                    }

                    Slider(value = progress,
                        onValueChange = { progress = it },
                        modifier = modifier.weight(1f),
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colors.secondary,
                            activeTrackColor = MaterialTheme.colors.secondary
                        )
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Add to playlist",
                            modifier = modifier
                                .size(45.dp, 45.dp),
                            tint = MaterialTheme.colors.secondary
                        )
                    }


                }
                Divider()
                AnimatedVisibility(visible = true,//playState,
                    modifier = modifier.padding(start = 15.dp, end = 15.dp),
                    enter = slideInHorizontally(),
                    exit = fadeOut()
                ) {
//                    Text(
//                        text = "Track title very very very very very very very very very very very very very very very very very very very very very very very very very long",
//                        modifier = modifier,
////                            .animateEnterExit(enter = slideInHorizontally(), exit = fadeOut(), "Long title"),
//                        color = MaterialTheme.colors.secondary,
//                        maxLines = 1
//                    )
//                    RunningText(text = "Track title very very very very very very very very very very very very very very very very very very very very very very very very very long" ,
//                        startAnimation = playState)
//                    RunningText_2(text = "Track title very very very very very very very very very very very very very very very very very very very very very very very very very long" )
                    RunningText_4(text = "Track title very very very very very very very very very very very very very very very very very very very very very very very very very long" ,
                        running = playState)
//                    RunningText_4(text = "Track title very very very very very very very very very very very very very very very very very very very very very very very very very long")
                }


            }

        }
    }

}

@ExperimentalAnimationApi
@Composable
fun RunningText(text : String, startAnimation : Boolean){
    var scrollState = rememberScrollState(0)
    Text(
        text = text,
        modifier = Modifier
            .horizontalScroll(scrollState),
        color = MaterialTheme.colors.secondary,
        maxLines = 1
    )

    var duration = text.length*100
    LaunchedEffect(startAnimation){
        scrollState.animateScrollTo(scrollState.maxValue,
            animationSpec =   infiniteRepeatable(
                animation = tween(duration, delayMillis = 1000),
                repeatMode = RepeatMode.Restart
            )
        )
    }

}

@Composable
fun RunningText_2(text : String, startAnimation : Boolean){
    var scrollState = rememberScrollState(0)
//    var startAnimationState = remember { mutableStateOf(startAnimation) }

    var duration = text.length*100
    AnimatedVisibility(visible = !startAnimation,//playState,
        enter = fadeIn(
            animationSpec =   repeatable(
                iterations = 5,
                animation = tween(500, duration),
                repeatMode = RepeatMode.Restart
            )//,
 //           initialOffset = {fullSize -> IntOffset(fullSize.width/4, 0)}

        ),
        exit = fadeOut()
    ){
        Text(
            text = text,
            modifier = Modifier
                .horizontalScroll(scrollState),
            color = MaterialTheme.colors.secondary,
            maxLines = 1
        )
    }



//    LaunchedEffect(scrollState){
//        scrollState.animateScrollTo(scrollState.maxValue,
//            animationSpec =   repeatable(
//                iterations = 1,
//                animation = tween(duration, delayMillis = 1000),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//        startAnimation = false
//    }

//    LaunchedEffect(key1 = scrollState.value == scrollState.maxValue){
//        scrollState.animateScrollTo(0,
//        animationSpec = fadeOut()
//        )
//    }



}

@ExperimentalAnimationApi
@Composable
fun RunningText_3(text : String, startAnimation : Boolean){
    var scrollState = rememberScrollState(0)
    var duration = text.length*100
    var alphaValue = scrollState.value.toFloat()/scrollState.maxValue.toFloat()
    var alphaValue2 by remember { mutableStateOf(1f)}


 //   Log.i("RunningText", "scrollState.maxValue = ${scrollState.maxValue}")

    Text(
        text = text,
        modifier = Modifier
            .horizontalScroll(state = scrollState, enabled = false)
            .graphicsLayer(alpha = alphaValue2),
        color = MaterialTheme.colors.secondary,
        maxLines = 1
    )


    LaunchedEffect(startAnimation){
        scrollState.animateScrollTo(scrollState.maxValue,
            animationSpec =   infiniteRepeatable(
                animation = tween(duration, delayMillis = 1100),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(scrollState.value == scrollState.maxValue-1){
 //       Log.i("RunningText", "scrollState.value = ${scrollState.value}")
 //       if(scrollState.value == 0){
            Log.i("RunningText", "start fading in alpha calculation")
            delay(100)
            alphaValue2 = 0f
            delay(900)
            for(i in 1..1000){
                delay(1)
                if(alphaValue2+0.001f > 1f) alphaValue2 = 1f
                else alphaValue2+=0.001f
                Log.i("RunningText", "alphaValue2 = $alphaValue2")
            }
            Log.i("RunningText", "max alphaValue2 = $alphaValue2")
//        }
    }

}

@ExperimentalAnimationApi
@Composable
fun RunningText_4(text : String, running : Boolean) {

    val listState = rememberLazyListState(Int.MAX_VALUE / 2)
    val items = listOf(text, "            ")
    val duration = text.length*60

    LazyRow(
        state = listState,
        userScrollEnabled = false,
    ) {
        items(Int.MAX_VALUE, itemContent = {
            val index = it % items.size
            Text(   text = items[index],
                    color = MaterialTheme.colors.secondary,
                    maxLines = 1)// item composable
        })
    }

    LaunchedEffect(running){
        if(running) autoScroll(listState)
    }

}

private tailrec suspend fun autoScroll(lazyListState: LazyListState) {
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(SCROLL_DX)
    }
    delay(DELAY_BETWEEN_SCROLL_MS)

    autoScroll(lazyListState)
}

private const val DELAY_BETWEEN_SCROLL_MS = 10L//8L
private const val SCROLL_DX = 2f//1f

@ExperimentalAnimationApi
@Preview
@Composable
fun AudioPlayControllerPreview()
{
    PlainAudioPlayerTheme {
        Column{
            AudioPlayController()
 //           RunningText("Track title very very very very very very very very very very very very very very very very very very very very very very very very very long")
        }

    }

}