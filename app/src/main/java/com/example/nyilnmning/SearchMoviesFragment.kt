package com.example.nyilnmning

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.service.DisplayService
import com.example.nyilnmning.view.PopularMoviesAdapterList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchMoviesFragment :  Fragment() {


    @Inject
    lateinit var repository: MovieRepository


    @Inject
    lateinit var service: DisplayService

    private lateinit var movieAdapter: PopularMoviesAdapterList




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        setupRecyclerView(view)
        sendSearch()

    }

    private fun sendSearch() {
        val queryField = view?.findViewById<EditText>(R.id.edit_query)
        var query = "";

        view?.findViewById<Button>(R.id.searchBtn)?.setOnClickListener {
            if (queryField != null) {
                query =  queryField.text.toString()
                lifecycleScope.launch {
                   val result =  service.searchMovie(query)
                    result.onSuccess { movies ->
                        movieAdapter.updateMovies(movies)

                    }.onFailure { error ->
                        Log.e("MainActivity", "Error: ${error.message}")
                    }
                }
            };
        };
    }

    private fun setupRecyclerView(view: View){

        val recyclerView = view.findViewById<RecyclerView>(R.id.transactionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = PopularMoviesAdapterList(emptyList())
        recyclerView.adapter = movieAdapter

    }


//    private fun randomMovie() {
//
//        lifecycleScope.launch {
//
//            val result = repository.searchMovie(query)
//
//            result.onSuccess { movies ->
//                   movieAdapter.updateMovies(movies)
//            }.onFailure { error ->
//                Log.e("MainActivity", "Error: ${error.message}")
//            }
//        }
//    }
}