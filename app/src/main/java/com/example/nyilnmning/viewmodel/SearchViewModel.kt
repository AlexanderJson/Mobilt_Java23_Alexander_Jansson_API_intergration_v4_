package com.example.nyilnmning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.frontpage.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: DisplayService) : ViewModel(){
        val movieLiveData = MutableLiveData<List<Movie>?>()
        val movies: LiveData<List<Movie>?> get() = movieLiveData

        fun searchMovies(query: String) {
        viewModelScope.launch {
            val fetchedMovies = repo.searchMovie(query)
            if (fetchedMovies.isSuccess){
                movieLiveData.value = fetchedMovies.getOrNull()
            } else{
                movieLiveData.value = emptyList()
            }
        }
    }



}

