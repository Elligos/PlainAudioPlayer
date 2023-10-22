package com.example.plainaudioplayer.data.core.network

import com.example.plainaudioplayer.BuildConfig
import com.example.plainaudioplayer.data.audio.remote.AudioApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


interface AudioHttpClient {

    val audioApi: AudioApi
}

class AudioHttpClientImpl @Inject constructor(
//    baseUrl : String,
) : AudioHttpClient {

    private val client = OkHttpClient.Builder()
        //.addInterceptor(QueryInterceptor(hashMapOf("api_key" to movieUrlProvider.apiKey)))
        .addInterceptor(HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE) )
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override val audioApi: AudioApi by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(AudioApi::class.java) }
}