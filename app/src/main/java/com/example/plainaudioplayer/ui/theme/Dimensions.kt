package com.example.plainaudioplayer.ui.theme

import androidx.compose.ui.unit.dp

// Roman Kamyshnikov — 16.02.2023, в 13:37
// Цвета и размеры теперь лучше объявлять в коде, например через object
// или просто в самих @Compsable функциях задавать размеры
object Dimensions {

    val padding_main = 32.dp
    val padding_medium = 16.dp
    val padding_small = 8.dp
    val padding_mini = 4.dp

    val custom_padding_big_vertical_button = 24.dp
    val custom_padding_vertical_button = 20.dp

    val roundedMini = 4.dp
    val roundedSmall = 8.dp

    val borderMini = 1.dp
    val borderSmall = 2.dp

    val control_icon_width = 65.dp
    val control_icon_height = 65.dp
    val audio_item_control_icon_width = 50.dp
    val audio_item_control_icon_height = 50.dp
    val music_icon_width = 50.dp
    val music_icon_height = 50.dp
}