package com.example.nyilnmning.service

import android.content.Context
import android.util.Log
import androidx.paging.PagingData
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.model.Genre
import com.example.nyilnmning.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisplayService @Inject constructor(private val repo: MovieRepository, private val recommendService: RecommendationService) {


    fun getTrendingFrontpage(): Flow<PagingData<Movie>> {
        return repo.getPopularMovies()
    }

    fun getRandomMovies(): Flow<PagingData<Movie>> {
        return repo.getRandom()

    }

    //TODO : a choice of seperating genre: Adventorous (value 3 at 60%), Safe (value 1 at 70%), Explorer (value 2 at 60%), Crazy (value 1,2,3 + 4,5 random)
      fun recommendByGenre(context: Context): Flow<PagingData<Movie>>  = flow {
        val topRatedGenres = recommendService.ratingPercentage(context)
        val genreIds =
            topRatedGenres.map { Genre.genreMap.entries.find { entry -> entry.value == it.first }?.key }
        Log.d("From display service: Genre ID: ", genreIds.toString())

        if (genreIds.isNotEmpty()) {
            val genreQuery = genreIds.joinToString(",")
            Log.d("Rating id,", genreQuery)
            emitAll(repo.getRecommended(genres = genreQuery))
            try {
                val recommendedMovies = repo.getRecommended(genres = genreQuery)
                Log.d("Rating fetched: ", recommendedMovies.toString())
                emitAll(emptyFlow())
            } catch (e: Exception) {
                Log.e("Rating Error", "Error message: ", e)
            }
        }

    }
}





//    private suspend fun discoverMovies(genres: String?): Result<List<Movie>> {
//        return withContext(Dispatchers.IO){
//            try {
//                val response = repo.discoverMovies()
//                Result.success(response.getOrNull() ?: emptyList())
//            } catch (e: Exception){
//                Result.failure(e)
//            }
//        }
//    }

//    suspend fun trendingMovies(): Result<List<Movie>> {
//        return withContext(Dispatchers.IO){
//            try {
//                val response = repo.getPopularMovies()
//                Result.success(response.getOrNull() ?: emptyList())
//            } catch (e: Exception){
//                Result.failure(e)
//            }
//        }
//    }

//    suspend fun searchMovie(query: String): Result<List<Movie>>{
//        return withContext(Dispatchers.IO){
//            try {
//                val response = repo.searchMovie(query)
//                Result.success(response.getOrNull() ?: emptyList())
//            } catch (e: Exception){
//                Result.failure(e)
//            }
//        }
//    }
//

//    suspend fun getRandomMovie(): Result<Movie> {
//        return withContext(Dispatchers.IO){
//            try{
//                val movies = discoverMovies(genres = null).getOrNull() ?: emptyList()
//                if (movies.isNotEmpty()) {
//                    Log.e("random", "Error: $movies")
//                    val randomMovie = movies.random()
//                    Result.success(randomMovie)
//
//            }
//                else{
//                    Result.failure(Exception("No movies found"))
//                    }
//            }catch (e: Exception){
//                Result.failure(e)
//            }
//
//            }
//        }
//    }

