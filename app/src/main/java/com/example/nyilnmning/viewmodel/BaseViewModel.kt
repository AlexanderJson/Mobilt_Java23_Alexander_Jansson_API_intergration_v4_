package com.example.nyilnmning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyilnmning.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData


    abstract fun searchMovies(query: String)

    abstract fun getMovie()

    protected fun handleError(exception: Exception){
        Log.e("BaseViewModel", "Error: ${exception.message}")
    }


}