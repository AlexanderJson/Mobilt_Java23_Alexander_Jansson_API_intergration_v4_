package com.example.nyilnmning.service

import android.content.Context
import android.util.Log
import com.example.bankapp.Users.repository.UserRepository
import com.example.nyilnmning.model.Genre
import com.example.nyilnmning.model.User
import com.example.nyilnmning.repository.RecommendationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationService @Inject constructor(private val recommendationRepository: RecommendationRepository) {




    // Extra logik här senare!
    suspend fun ratingPercentage(): List<Pair<String,Double>> {
        return withContext(Dispatchers.IO) {
            try {
                val favoriteGenres = recommendationRepository.allGenreRatings()

                val topGenres = favoriteGenres.map { (genre, percent)  ->
                    genre.toDouble() to percent.replace("%", "").toDouble()
                }
                    .sortedByDescending { it.second }
                    .take(3)

                val genreData = Genre.genreMap

                val topRatedGenres = topGenres.map { (genreId, percent) ->
                    val genreName = genreData[genreId.toInt()] ?: "Unknown"
                    Log.d("Rating",genreName)
                    genreName to percent
                }

                topRatedGenres.forEach{(genre,percent) ->
                    Log.d("Rating success", "Top genres: $genre, $percent%")
                }

                return@withContext topRatedGenres
            } catch (e: Exception) {
                Log.e("Rating Percentage Failure", "Failed to fetch!")
                emptyList()
            }
        }
    }
}