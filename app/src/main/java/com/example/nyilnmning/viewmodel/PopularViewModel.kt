package com.example.nyilnmning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.frontpage.DisplayService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(private val service: DisplayService) : ViewModel(){

    val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = movieLiveData



    }


