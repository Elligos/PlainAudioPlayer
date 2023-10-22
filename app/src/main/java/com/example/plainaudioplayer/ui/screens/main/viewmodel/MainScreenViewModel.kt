package com.example.plainaudioplayer.ui.screens.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plainaudioplayer.data.audio.AudioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TEXT_ENTERED_DEBOUNCE_MILLIS = 500L

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val audioRepository: AudioRepository,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _searchResult = MutableStateFlow("")
    val searchResult: StateFlow<String> = _searchResult
        .asStateFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .sample(TEXT_ENTERED_DEBOUNCE_MILLIS)
                .mapLatest(::handleQuery)
                .collect()
        }
    }

    fun onNewQuery(query: String) {
//        _searchResult.update { value ->
//            value.copy(
//                query = query,
//            )
//        }
        queryFlow.value = query
    }
//
    private suspend fun handleQuery(query: String) {
        //emitShowOrHideProgress(query)

        if (query.isNotEmpty()) {
            handleSearchAudio(query)
        }
    }
//
//    private fun emitShowOrHideProgress(query: String) {
//        _searchResult.update { value ->
//            if (query.isEmpty()) {
//                value.copy(
//                    isMoviesLoading = false,
//                    movies = emptyList(),
//                    resultPlaceholder = R.string.movies_placeholder,
//                )
//            } else {
//                value.copy(
//                    isMoviesLoading = true,
//                )
//            }
//        }
//    }
//
    /*private */suspend fun handleSearchAudio(query: String) {
        // emulate a small delay in delivering the movies as it can be quite fast
        delay(1_000L)

        val searchResult = audioRepository.searchMusic(query)
        Log.d("MainScreenViewModel", "Audio search result: $searchResult")
        _searchResult.update { searchResult }


//        when (val moviesResult = audioRepository.searchMoviesWithReviews(query)) {
//            is Error -> emitErrorState()
//            is Success -> emitSuccessState(moviesResult.result)
//        }
    }
//
//    private fun emitErrorState() {
//        _searchResult.update { value ->
//            value.copy(
//                isMoviesLoading = false,
//                movies = emptyList(),
//                resultPlaceholder = R.string.search_error,
//            )
//        }
//    }
//
//    private fun emitSuccessState(movies: List<SearchMovieWithMyReview>) {
//        _searchResult.update { value ->
//            value.copy(
//                isMoviesLoading = false,
//                movies = movies,
//                resultPlaceholder = if (movies.isEmpty()) R.string.empty_result else null,
//            )
//        }
//    }
}