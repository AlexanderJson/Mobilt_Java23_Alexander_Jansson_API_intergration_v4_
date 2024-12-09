package com.example.nyilnmning.repository

import android.util.Log
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.api.ApiInterfaceRecommendations
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.RatingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationRepository @Inject constructor(private val api: ApiInterfaceRecommendations) {


    suspend fun registerLike(movie: Movie, userID: Int): Result<String>{
        return withContext(Dispatchers.IO){
            try {
                val genreID = movie.genre_ids.getOrNull(0)
               val ratingData = RatingData(
                        filmID = movie.id,
                        genreID = genreID,
                        userID = userID,
                        rating = 5.0f
                    )
                val response = api.addRating(ratingData)
                if (response.isSuccessful){
                    Result.success("Movie rating succeeded!!")
                } else {
                    Result.failure(Exception("Failed to rate"))
                }
            } catch (e: Exception){
                Log.e("UserRepository", "Error liking movie", e)
                Result.failure(Exception("Failed to like movie", e))
            }
        }
    }

    suspend fun allGenreRatings(): Map<String, String> {
        return withContext(Dispatchers.IO) {
            try {
                val recommendations = api.getRecommendations()
                Log.d("Rating Percentage Response: ", recommendations.toString())
                recommendations
            } catch (e: Exception) {
                Log.e("Rating Percentage Failure", "Failed to fetch!" + e.message)
                emptyMap()
            }
        }
    }
}