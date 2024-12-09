package com.example.nyilnmning.service

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.bankapp.Users.repository.UserRepository
import com.example.nyilnmning.model.Genre
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.User
import com.example.nyilnmning.repository.RecommendationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationService @Inject constructor(private val recommendationRepository: RecommendationRepository) {


    private fun getUserID(context: Context): Int? {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sp = EncryptedSharedPreferences.create(
            context,
            "MyPrefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val userIDString =  sp.getString("token", null)
        return userIDString?.toIntOrNull()
    }

    suspend fun likeMovie(movie: Movie, context: Context){
        val userID = getUserID(context)
        Log.d("userid", userID.toString())
        if (userID != null) {
            recommendationRepository.registerLike(movie,userID)
        }
    }

    // Extra logik här senare!
    suspend fun ratingPercentage(context: Context): List<Pair<String,Double>> {
        return withContext(Dispatchers.IO) {
            try {
                val userID = getUserID(context)

                val favoriteGenres = userID?.let { recommendationRepository.allGenreRatings(it) }

                val topGenres = favoriteGenres?.map { (genre, percent)  ->
                    genre.toDouble() to percent.replace("%", "").toDouble()
                }
                    ?.sortedByDescending { it.second }
                    ?.take(3)

                val genreData = Genre.genreMap

                val topRatedGenres = topGenres?.map { (genreId, percent) ->
                    val genreName = genreData[genreId.toInt()] ?: "Unknown"
                    Log.d("Rating",genreName)
                    genreName to percent
                }
                Log.d("From recommendation", ": Genre ID: $topRatedGenres")

                topRatedGenres?.forEach{ (genre,percent) ->
                    Log.d("Rating success", "Top genres: $genre, $percent%")
                }
                return@withContext topRatedGenres!!
            } catch (e: Exception) {
                Log.e("Rating Percentage Failure", "Failed to fetch!")
                emptyList()
            }
        }
    }
}