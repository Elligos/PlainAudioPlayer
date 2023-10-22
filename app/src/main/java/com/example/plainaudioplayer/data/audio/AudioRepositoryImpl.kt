package com.example.plainaudioplayer.data.audio

import com.example.plainaudioplayer.data.audio.remote.AudioRemoteDataSource
import javax.inject.Inject


class AudioRepositoryImpl @Inject constructor(
    private val audioRemoteDataSource: AudioRemoteDataSource,
) : AudioRepository {

    override suspend fun searchMusic(query: String): String {
        return audioRemoteDataSource.searchMusic(query)
    }

//    override suspend fun searchMoviesWithReviews(query: String): String=
//        searchMusic(query)
//            .mapSuccess { movies -> fillSearchMoviesWithMyReviews(movies) }

    //    private suspend fun searchMovies(query: String): Result<List<SearchMovie>, Throwable> =
//        moviesRemoteDataSource.searchMovies(query)
//            .mapSuccess { dtos -> dtos.map { singleDto -> singleDto.toEntity(movieUrlProvider.baseImageUrl) } }
//            .doOnSuccess { movieEntities -> moviesLocalDataSource.insertAll(movieEntities) }
//            .mapSuccess { entries -> entries.map { movieEntity -> movieEntity.toSearchMovie() } }

}