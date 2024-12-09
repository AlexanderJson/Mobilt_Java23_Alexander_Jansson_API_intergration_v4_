package com.example.nyilnmning.api

import com.example.nyilnmning.model.MovieResponse
import com.example.nyilnmning.model.RatingData
import com.example.nyilnmning.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterfaceRecommendations {


    // RecommendationFilter REST Api

    // fix arguments here when changing python code to send userid
    @POST("/recommend/genre")
    suspend fun getRecommendations(
        @Body user: User
    ): Map<String, String>



    @POST("/add_rating")
    suspend fun addRating(
        @Body ratingData: RatingData
    ): Response<Void>

}