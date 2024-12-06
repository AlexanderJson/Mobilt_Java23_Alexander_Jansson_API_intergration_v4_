package com.example.nyilnmning.api

import com.example.nyilnmning.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterfaceRecommendations {


    // RecommendationFilter REST Api

    // fix arguments here when changing python code to send userid
    @GET("/recommend/genre")
    suspend fun getRecommendations(): Map<String,String>

}