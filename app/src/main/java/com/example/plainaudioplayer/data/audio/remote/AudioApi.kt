package com.example.plainaudioplayer.data.audio.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface AudioApi {
    @GET("music/trending")
    suspend fun getTrendingMusic(): String

    @GET("search")
    suspend fun searchMusic(
        @Query("q") query: String?,
//        @Query("show") show: String?,
//        @Query("sort") sort: String?,
//        @Query("page") page: Int?,
//        @Query("limit") limit: Int?,
//        @Query("genre") genre: String?
    ): String
}