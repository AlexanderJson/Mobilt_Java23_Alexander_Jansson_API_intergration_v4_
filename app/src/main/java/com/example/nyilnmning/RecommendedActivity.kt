package com.example.nyilnmning

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.view.PopularMoviesAdapterList
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecommendedActivity :  AppCompatActivity() {




    @Inject
    lateinit var repository: MovieRepository






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trending)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        lifecycleScope.launch {
            val result = repository.getPopularMovies()
            result.onSuccess { movies ->
                for (movie in movies){
                    Log.d("Main", "Movie: ${movie.title}")
                    val randomMovie = movies[0]
                    val title = findViewById<TextView>(R.id.titleHeader)
                    val synopsis = findViewById<TextView>(R.id.randomOverviewText)
                    title.text = randomMovie.title
                    synopsis.text = randomMovie.overview
                    val url = "https://image.tmdb.org/t/p/w500${randomMovie.poster_path}"


                    Glide.with(this@RecommendedActivity)
                        .load(url)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(findViewById(R.id.graphImage))
                }
            }.onFailure { error ->
                Log.e("MainActivity", "Error: ${error.message}")
            }
        }


        val randomBtn = findViewById<Button>(R.id.randomMovieBtn)
        randomBtn.setOnClickListener{

        }


        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigatonView.selectedItemId = R.id.nav_recommended

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_trending -> {
                    val intent = Intent(this, TrendingActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }
                R.id.nav_recommended -> {
                    true
                }

                R.id.nav_rate -> {
                    val intent = Intent(this, ReviewActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }

                else -> {
                    false
                }
            }
        }


    }
        private fun randomMovie(){}

}