package com.example.nyilnmning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.repository.ImageRepo
import com.example.nyilnmning.service.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PosterImageViewModel @Inject constructor(private val service: DisplayService) : ViewModel()
{
    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData


    fun getTrending(){
        viewModelScope.launch {
            val fetchedMovies = service.trendingMovies()
            movieLiveData.value = fetchedMovies.getOrNull()
        }
    }

    fun getRecommended(level: Int){
        viewModelScope.launch {
            val fetchedMovies = service.trendingMovies()
            movieLiveData.value = fetchedMovies.getOrNull()
        }
    }

}