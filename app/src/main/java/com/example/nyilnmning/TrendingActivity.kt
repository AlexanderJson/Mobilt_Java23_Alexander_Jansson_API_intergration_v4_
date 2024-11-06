//package com.example.nyilnmning
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.nyilnmning.repository.MovieRepository
//import com.example.nyilnmning.service.DisplayService
//import com.example.nyilnmning.view.PopularMoviesAdapterList
//import com.example.nyilnmning.viewmodel.TrendingViewModel
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class TrendingActivity : AppCompatActivity() {
//
//
//    @Inject
//    lateinit var message: String
//
//    @Inject
//    lateinit var repository: MovieRepository
//
//
//    private lateinit var movieAdapter: PopularMoviesAdapterList
//
//
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        println(message)
//
//
//        val recyclerView = findViewById<RecyclerView>(R.id.transactionsRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//       lifecycleScope.launch {
//           val result = repository.getPopularMovies()
//           result.onSuccess { movies ->
//               for (movie in movies){
//                   Log.d("Main", "Movie: ${movie.title}")
//                   movieAdapter = PopularMoviesAdapterList(movies)
//                   recyclerView.adapter = movieAdapter
//               }
//           }.onFailure { error ->
//               Log.e("MainActivity", "Error: ${error.message}")
//           }
//       }
//
//
//
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//
//
//    }
//}