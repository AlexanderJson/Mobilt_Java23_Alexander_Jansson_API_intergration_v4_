package com.example.nyilnmning.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.bankapp.Users.service.UserService
import com.example.nyilnmning.R
import com.example.nyilnmning.service.DisplayService
import com.example.nyilnmning.viewmodel.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RandomMovieFragment :  Fragment() {


    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var service: DisplayService
    val randomViewModel: RandomViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_random_movie, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        randomMovie()




        val nextBtn = view.findViewById<Button>(R.id.nextBtn)
        nextBtn.setOnClickListener{
                randomMovie()
        }





    }



    private fun randomMovie() {
        viewLifecycleOwner.lifecycleScope.launch {
            randomViewModel.getRandomMovie()

            randomViewModel.movies.observe(viewLifecycleOwner) { movies ->
                val title = view?.findViewById<TextView>(R.id.titleHeader)
                val synopsis = view?.findViewById<TextView>(R.id.randomOverviewText)
                movies?.forEach { movie ->
                Log.d("From movie random", "Movie: ${movie.title}")
                if (title != null) {
                    title.text = movie.title
                }
                if (synopsis != null) {
                    synopsis.text = movie.overview
                }
                val url = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                view?.let {
                    Glide.with(this@RandomMovieFragment)
                        .load(url)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(it.findViewById(R.id.graphImage))
                }
            }
        }
        }
    }
}

    /*

        private fun randomMovie(){
            Log.d("rANDOMin", "iniiit")
            randomViewModel.movies.observe(viewLifecycleOwner) { movies ->
                val title = view?.findViewById<TextView>(R.id.titleHeader)
                val synopsis = view?.findViewById<TextView>(R.id.randomOverviewText)
                movies?.forEach { movie ->
                Log.d("rANDOMin", "Movie: ${movie.title}")
                     if (title != null) {
                         title.text = movie.title
                     }
                     if (synopsis != null) {
                         synopsis.text = movie.overview
                     }
                     val url = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                     view?.let {
                         Glide.with(this@RandomMovieFragment)
                             .load(url)
                             .placeholder(R.drawable.ic_launcher_background)
                             .error(R.drawable.ic_launcher_background)
                             .into(it.findViewById(R.id.graphImage))
                     }
                 }

                }
            }
*/




