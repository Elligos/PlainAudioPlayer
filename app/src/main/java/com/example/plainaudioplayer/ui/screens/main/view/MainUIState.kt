package com.example.plainaudioplayer.ui.screens.main

import com.example.plainaudioplayer.data.AudioDetail

data class MainUITrackState(
    var trackSelected : Boolean = false,
    var trackPlaying : Boolean = false,
    var trackSelectedIndex : Int = 0,
    var trackPlayedTime : Int = 0,
    var trackList : List<AudioDetail> = emptyList()
)
