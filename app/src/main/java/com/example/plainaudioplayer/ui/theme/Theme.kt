package com.example.plainaudioplayer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
        primary = Orange900,
        primaryVariant = Orange600,
        secondary = Grey400,
        background = Brown,
        surface = Black,
        onPrimary = Grey900,
        onBackground = Grey100,
        onSurface = Grey200
)

private val LightColorPalette = lightColors(
        primary = Orange900,
        primaryVariant = Orange600,
        secondary = Grey400,
        background = Grey700,
        surface = Grey800,
        onPrimary = Grey900,
        onBackground = Grey100,
        onSurface = Grey200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PlainAudioPlayerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}