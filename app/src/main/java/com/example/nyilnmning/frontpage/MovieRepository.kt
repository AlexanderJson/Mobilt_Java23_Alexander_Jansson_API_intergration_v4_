package com.example.nyilnmning.frontpage

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class MovieRepository @Inject constructor(private val api: ApiInterface) {
    private val apiKey = "b89807eb08b8124fbb7f608b7511d1a0"; //todo env


       fun getPopularMovies(): Flow<PagingData<Movie>> {                  // use a Flow to continously fetch data over time (pager().flow)
           return Pager(                                                  // code within also becomes suspendable
           config = PagingConfig(
               pageSize = 20,
               enablePlaceholders = false //TODO
           ),
           pagingSourceFactory = { MoviePagingSrc(api,apiKey) }
       ).flow
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


//   suspend fun getPopularMovies(): Result<List<Movie>> {
//       return withContext(Dispatchers.IO){
//           try {
//               val response = api.getPopular(apiKey)
//               Result.success(response.results)
//           } catch (e: Exception){
//               Result.failure(e)
//           }
//       }
//   }