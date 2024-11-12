package com.example.nyilnmning.service

import android.util.Log
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

@Singleton
class DisplayService @Inject constructor(private val repo: MovieRepository) {

    private suspend fun discoverMovies(): Result<List<Movie>> {
        return withContext(Dispatchers.IO){
            try {
                val response = repo.discoverMovies()
                Result.success(response.getOrNull() ?: emptyList())
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }
    suspend fun trendingMovies(): Result<List<Movie>> {
        return withContext(Dispatchers.IO){
            try {
                val response = repo.getPopularMovies()
                Result.success(response.getOrNull() ?: emptyList())
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }

    suspend fun searchMovie(query: String): Result<List<Movie>>{
        return withContext(Dispatchers.IO){
            try {
                val response = repo.searchMovie(query)
                Result.success(response.getOrNull() ?: emptyList())
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }

    suspend fun getRandomMovie(): Result<Movie> {
        return withContext(Dispatchers.IO){
            try{
                val movies = discoverMovies().getOrNull() ?: emptyList()
                if (movies.isNotEmpty()) {
                    Log.e("random", "Error: ${movies}")
                    val randomMovie = movies.random()
                    Result.success(randomMovie)

            }
                else{
                    Result.failure(Exception("No movies found"))
                    }
            }catch (e: Exception){
                Result.failure(e)
            }

            }
        }
    }

