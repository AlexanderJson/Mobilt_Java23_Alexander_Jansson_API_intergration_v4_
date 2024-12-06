package com.example.nyilnmning.repository

import android.util.Log
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val api: ApiInterface) {
    private val apiKey = "b89807eb08b8124fbb7f608b7511d1a0"; //todo env

   suspend fun getPopularMovies(): Result<List<Movie>> {
       return withContext(Dispatchers.IO){
           try {
               val response = api.getPopular(apiKey)
               Result.success(response.results)
           } catch (e: Exception){
               Result.failure(e)
           }
       }
   }

    suspend fun discoverMovies(genres:String? = null, sortBy: String? = null, limit: Int? = null): Result<List<Movie>> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getDiscover(apiKey,genres = genres)
                Result.success(response.results)
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }

    suspend fun searchMovie(query: String): Result<List<Movie>>  {
        return withContext(Dispatchers.IO){
            try {
                val response = api.searchMovie(apiKey,query)
                Result.success(response.results)
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }
}