package com.example.plainaudioplayer.di

import com.example.plainaudioplayer.data.audio.AudioRepository
import com.example.plainaudioplayer.data.audio.AudioRepositoryImpl
import com.example.plainaudioplayer.data.audio.remote.AudioRemoteDataSource
import com.example.plainaudioplayer.data.audio.remote.AudioRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface AudioModule {

    @Binds
    fun bindMoviesRemoteDataSource(
        impl: AudioRemoteDataSourceImpl,
    ): AudioRemoteDataSource

//    @Binds
//    fun bindMoviesLocalDataSource(
//        impl: MoviesLocalDataSourceImpl,
//    ): MoviesLocalDataSource
//
    @Binds
    fun bindMoviesRepository(
        impl: AudioRepositoryImpl,
    ): AudioRepository
}