package com.example.nyilnmning

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.service.DisplayService
import com.example.nyilnmning.view.PopularMoviesAdapterList
import com.example.nyilnmning.viewmodel.TrendingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReviewActivity : AppCompatActivity() {



    @Inject
    lateinit var repository: MovieRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
                    val intent = Intent(this, RecommendedActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }

                R.id.nav_rate -> {
                    true
                }

                else -> {
                    false
                }
            }
        }


    }
}