package com.example.plainaudioplayer.data.audio.remote

import javax.inject.Inject


interface AudioRemoteDataSource {

    suspend fun searchMusic(query : String) : String

    //suspend fun searchMovies(query: String): Result<List<SearchMovieDto>, Throwable>

    //suspend fun getMovieDetails(id: MovieId): Result<DetailsMovieDto, Throwable>
}

class AudioRemoteDataSourceImpl @Inject constructor(
    private val audioApi: AudioApi,
) : AudioRemoteDataSource {

    override suspend fun searchMusic(query: String): String {
        return audioApi.searchMusic("")
    }

//    override suspend fun searchMovies(query: String): Result<List<SearchMovieDto>, Throwable> =
//        runOperationCatching { moviesApi.searchMovie(query).searchMovies }
//            .doOnError { error -> Timber.e("Search movies from server error", error) }
//
//    override suspend fun getMovieDetails(id: MovieId): Result<DetailsMovieDto, Throwable> =
//        runOperationCatching { moviesApi.getMovieDetails(id) }
//            .doOnError { error -> Timber.e("getMovieDetails from server error", error) }
}