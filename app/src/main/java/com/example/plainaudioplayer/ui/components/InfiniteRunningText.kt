package com.example.plainaudioplayer.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay


private const val DELAY_BETWEEN_SCROLL_MS = 8L

private const val SMALL_SCROLL_DX = 2f
private const val MEDIUM_SCROLL_DX = 4f
private const val BIG_SCROLL_DX = 8f

private const val SPACE_BETWEEN_TEXT = "            "


sealed class RunningTextSpeed{
    object SLOW_SPEED : RunningTextSpeed()
    object MEDIUM_SPEED : RunningTextSpeed()
    object FAST_SPEED : RunningTextSpeed()
}

@ExperimentalAnimationApi
@Composable
fun InfiniteRunningText(text : String,
                        textStyle: TextStyle = MaterialTheme.typography.body1,
                        running : Boolean,
                        speed : RunningTextSpeed = RunningTextSpeed.MEDIUM_SPEED) {

    val listState = rememberLazyListState(Int.MAX_VALUE / 2)

    //TODO: calculate space between text instead of using constant value
    val items = listOf(SPACE_BETWEEN_TEXT, text )

    LazyRow(
        state = listState,
        userScrollEnabled = false,
    ) {
        items(Int.MAX_VALUE, itemContent = {
            val index = it % items.size
            Text(   text = items[index],
                color = MaterialTheme.colors.onSurface,//MaterialTheme.colors.secondary,
                maxLines = 1,
                style =  textStyle)// item composable
        })
    }

    LaunchedEffect(running){
        if(running) autoScroll(listState, speed)
    }

}

private tailrec suspend fun autoScroll(lazyListState: LazyListState,
                                       speed : RunningTextSpeed) {
    val scrollDx = when(speed){
        is RunningTextSpeed.SLOW_SPEED -> SMALL_SCROLL_DX
        is RunningTextSpeed.MEDIUM_SPEED -> MEDIUM_SCROLL_DX
        is RunningTextSpeed.FAST_SPEED -> BIG_SCROLL_DX
    }
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(scrollDx)
    }
    delay(DELAY_BETWEEN_SCROLL_MS)

    autoScroll(lazyListState,speed)
}

