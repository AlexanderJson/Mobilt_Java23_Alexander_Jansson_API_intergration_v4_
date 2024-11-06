package com.example.nyilnmning.api

import android.telecom.Call
import com.example.nyilnmning.MainActivity
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.MovieResponse
import dagger.Component
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton



interface ApiInterface {


    /* Suspendable funktioner. Fördelaktigt huvudsakligen av två anledningar:
    Kan göra på bästa lediga tråd, plus hoppar av tråd när den inväntar data (suspend)
    * */

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("discover/movie")
    suspend fun getDiscover(
        @Query("api_key") apiKey: String,
        @Query("query") query: String? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("with_genres") genres: String? = null,
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse

}