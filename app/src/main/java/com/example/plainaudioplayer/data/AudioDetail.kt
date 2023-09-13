package com.example.plainaudioplayer.data

import androidx.compose.runtime.Immutable

@Immutable
data class AudioDetail(
    val artist: String = "",
    val track: String = "",
    val trackDuration_s: Int = 0
)
