package com.example.plainaudioplayer.di

import com.example.plainaudioplayer.data.audio.remote.AudioApi
import com.example.plainaudioplayer.data.core.network.AudioHttpClient
import com.example.plainaudioplayer.data.core.network.AudioHttpClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindAudioHttpClient(
        impl: AudioHttpClientImpl,
    ): AudioHttpClient
}

@Module
@InstallIn(SingletonComponent::class)
object ApiWrapperModule {

    @Provides
    @Singleton
    fun provideAudioApi(client: AudioHttpClient): AudioApi = client.audioApi
}