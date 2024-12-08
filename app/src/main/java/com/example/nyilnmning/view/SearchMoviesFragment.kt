package com.example.nyilnmning.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nyilnmning.R
import com.example.nyilnmning.frontpage.MovieRepository
import com.example.nyilnmning.frontpage.DisplayService
import com.example.nyilnmning.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.fragment.app.viewModels
import com.example.nyilnmning.adapter.PopularMoviesAdapterList


@AndroidEntryPoint
class SearchMoviesFragment :  Fragment() {


    @Inject
    lateinit var repository: MovieRepository


    @Inject
    lateinit var service: DisplayService

    private lateinit var movieAdapter: PopularMoviesAdapterList

    private val searchViewModel: SearchViewModel by viewModels()


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



        observeViewModel()
        setupSearch()
        setupRecyclerView(view)

    }



    private fun observeViewModel(){
        searchViewModel.movies.observe(viewLifecycleOwner){movies ->
            if (movies != null){
                movieAdapter.updateMovies(movies)

                Log.d("SearchMoviesFragment", "Movies received: $movies")
            }else {
                Log.e("Viewmodel Search", "error etc")
            }
        }
    }

    private fun setupSearch(){
        val queryField = view?.findViewById<EditText>(R.id.edit_query)
        view?.findViewById<Button>(R.id.searchBtn)?.setOnClickListener{
            val query = queryField?.text.toString()
            if (query.isNotEmpty()){
                searchViewModel.searchMovies(query)
            }
        }
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