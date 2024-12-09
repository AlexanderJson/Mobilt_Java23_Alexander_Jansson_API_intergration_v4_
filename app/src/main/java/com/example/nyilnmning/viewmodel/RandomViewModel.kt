package com.example.nyilnmning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.service.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(private val service: DisplayService) : ViewModel(){

    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData


    fun searchMovies(query: String) {

    }


        fun getRandomMovie() {
            Log.e("lflkfkf", "hfifghpijspojbplodjdbj")
//        viewModelScope.launch {
//            val result = service.getRandomMovie()
//            result.onSuccess { movie ->
//                movieLiveData.value = listOf(movie) ?: emptyList()
//            }
//        }
    }

     fun getMovie() {
         Log.e("lflkfkf", "hfifghpijspojbplodjdbj")
//        viewModelScope.launch {
//            Log.e("Fetching the random movie", "")
//            val fetchedMovies = service.getRandomMovie()
//            val movie = fetchedMovies.getOrNull()
//
//            movieLiveData.value = movie?.let { listOf(it) } ?: emptyList()
//            if (movie != null) {
//                Log.e("random", "Error: ${movie.title}")
//            }
//
//        }
    }


}

