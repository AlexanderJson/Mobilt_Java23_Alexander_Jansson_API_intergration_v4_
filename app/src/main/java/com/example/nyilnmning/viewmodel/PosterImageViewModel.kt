package com.example.nyilnmning.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.service.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PosterImageViewModel @Inject constructor(
    private val service: DisplayService,
    application: Application) : AndroidViewModel(application)
{
        val context: Context = application.applicationContext
    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData


    val trendingFrontPage: Flow<PagingData<Movie>> = service.getTrendingFrontpage()
        .cachedIn(viewModelScope)


    val recommendedFrontPage: Flow<PagingData<Movie>> = service.recommendByGenre(context)
        .cachedIn(viewModelScope)

}



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