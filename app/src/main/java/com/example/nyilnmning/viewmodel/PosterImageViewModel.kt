package com.example.nyilnmning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.frontpage.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PosterImageViewModel @Inject constructor(private val service: DisplayService) : ViewModel()
{
    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData


    val trendingFrontPage: Flow<PagingData<Movie>> = service.getTrendingFrontpage()
        .cachedIn(viewModelScope)

//    fun getTrending(){
//        viewModelScope.launch {
//            val fetchedMovies = service.trendingMovies()
//            movieLiveData.value = fetchedMovies.getOrNull()
//        }
//    }

//    fun getRecommended(level: Int){
//        viewModelScope.launch {
//            val fetchedMovies = service.trendingMovies()
//            movieLiveData.value = fetchedMovies.getOrNull()
//        }
//    }

}