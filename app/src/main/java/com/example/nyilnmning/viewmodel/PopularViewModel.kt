package com.example.nyilnmning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.service.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(private val service: DisplayService) : ViewModel(){

    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData

     fun getMovie() {
        viewModelScope.launch {
            val fetchedMovies = service.trendingMovies()
            movieLiveData.value = fetchedMovies.getOrNull()
        }
    }

    }


