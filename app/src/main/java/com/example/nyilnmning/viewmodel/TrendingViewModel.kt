package com.example.nyilnmning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel(){

    private val movieLiveData = MutableLiveData<List<Movie>?>()
    val movies: MutableLiveData<List<Movie>?> get() = movieLiveData


    fun getMovies() {
        viewModelScope.launch {
            val fetchedMovies = repo.getPopularMovies()
            movieLiveData.value = fetchedMovies.getOrNull()
        }
    }
}




/*
    private val transactionLiveData = MutableLiveData<List<Transaction>?>()
    val transactions: MutableLiveData<List<Transaction>?> get() = transactionLiveData

    fun getTransactions(context: Context){
        viewModelScope.launch {
            val fetchedTransactions = transactionService.getTransaction()
            transactionLiveData.value = fetchedTransactions

        }
    }
*/