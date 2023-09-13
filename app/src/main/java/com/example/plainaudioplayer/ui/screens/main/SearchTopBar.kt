package com.example.plainaudioplayer.ui.screens.main

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plainaudioplayer.R
import com.example.plainaudioplayer.ui.theme.Dimensions
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme

@ExperimentalAnimationApi
@Composable
fun SearchTopBar(
    navigateUp: () -> Unit,
    onQueryChange: (searchQuery: String) -> Unit,
    searchHint: String,
    closeKeyboard: () -> Unit?
) {
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }

    // Handle clear icon state whether to show or hide based on query.
    var showClearQueryIcon: Boolean by rememberSaveable { mutableStateOf(false) }
    // Initially the icon state will be false and hidden since query will be empty.
    if (query.isEmpty()) {
        // If query is not empty show the icon
        showClearQueryIcon = false
    } else if (query.isNotEmpty()) {
        // If query is empty hide the icon
        showClearQueryIcon = true
    }

    // Detects whether a current keyboard is opened or closed
//    val keyboardState: KeyboardState by getCurrentKeyboardState()

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        val recordedSpeech = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        if (!recordedSpeech.isNullOrEmpty()) {
            query = recordedSpeech[0]
            // Perform query with the recorded query string.
            onQueryChange(query)
        }
    }
    Card(
        modifier = Modifier
            .padding(Dimensions.padding_mini)
            .border(
                Dimensions.borderSmall,
                MaterialTheme.colors.primary,
                RoundedCornerShape(Dimensions.roundedSmall)
            )
        ,
        shape = RoundedCornerShape(Dimensions.roundedSmall),
        backgroundColor = MaterialTheme.colors.surface
    ){
        Column() {

            // This Spacer avoids colliding content with app bar by matching the height of status bar.
            Spacer(Modifier.statusBarsPadding())

            TextField(
                value = query,
                onValueChange = { newQuery ->
                    // If user makes changes to text, immediately updated it.
                    query = newQuery
                    // To avoid crash, only query when string isn't empty.
                    if (newQuery.isNotEmpty()) {
                        // Pass latest query to refresh search results.
                        onQueryChange(newQuery)
                    }
                },
                leadingIcon = {
                    IconButton(
                        modifier = Modifier.padding(start = 4.dp),
                        onClick = {
//                        closeKeyboardAndNavigateUp(
//                            navigateUp = navigateUp,
//                            closeKeyboard = closeKeyboard,
//                            keyboardState = keyboardState
//                        )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = ""//stringResource(id = R.string.cd_search_icon)
                        )
                    }
                },
                trailingIcon = {
                    if (showClearQueryIcon) {
                        IconButton(
                            onClick = {
                                query = ""
                                closeKeyboard()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                tint = MaterialTheme.colors.primary,
                                contentDescription = ""//stringResource(id = R.string.cd_clear_icon)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                // This starts the activity and populates the intent with the speech text.
                                resultLauncher.launch(createLaunchSpeechRecognitionIntent)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_mic_24),
                                tint = MaterialTheme.colors.primary,
                                contentDescription = "",
                            )
                        }
                    }
                },
                placeholder = { Text(text = searchHint) },
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface, RectangleShape),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent//MaterialTheme.colors.surface
                )
            )

            // Divides content and search bar with line.
            //AppDivider(verticalPadding = 0.dp)
        }
    }

}

// Create an intent that can start the Speech Recognizer activity
private val createLaunchSpeechRecognitionIntent = Intent(
    RecognizerIntent.ACTION_RECOGNIZE_SPEECH
).apply {
    putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
}

@Preview
@ExperimentalAnimationApi
@Composable
private fun SearchAppBarLightPreview() {
    Column() {
        PlainAudioPlayerTheme(darkTheme = true) {
            SearchTopBar(
                navigateUp = { },
                onQueryChange = { },
                searchHint = "",//stringResource(id = R.string.hint_search_query_actors),
                closeKeyboard = { }
            )
        }
        PlainAudioPlayerTheme(darkTheme = false) {
            SearchTopBar(
                navigateUp = { },
                onQueryChange = { },
                searchHint = "",//stringResource(id = R.string.hint_search_query_movies),
                closeKeyboard = { }
            )
        }
    }

}

//@Preview
//@ExperimentalAnimationApi
//@Composable
//private fun SearchAppBarDarkPreview() {
//    PlainAudioPlayerTheme(darkTheme = false) {
//        SearchTopBar(
//            navigateUp = { },
//            onQueryChange = { },
//            searchHint = "",//stringResource(id = R.string.hint_search_query_movies),
//            closeKeyboard = { }
//        )
//    }
//}