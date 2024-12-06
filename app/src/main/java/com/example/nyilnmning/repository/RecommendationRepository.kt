package com.example.nyilnmning.repository

import android.util.Log
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.api.ApiInterfaceRecommendations
import com.example.nyilnmning.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationRepository @Inject constructor(private val api: ApiInterfaceRecommendations) {

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