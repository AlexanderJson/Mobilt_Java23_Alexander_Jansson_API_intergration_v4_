package com.example.nyilnmning.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.bankapp.Users.service.UserService
import com.example.nyilnmning.R
import com.example.nyilnmning.service.DisplayService
import com.example.nyilnmning.service.RecommendationService
import com.example.nyilnmning.viewmodel.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecommendedFragment :  Fragment() {


    @Inject
    lateinit var service: RecommendationService
    @Inject
    lateinit var movieService: DisplayService
    val randomViewModel: RandomViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_movie_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ratingPercentage()
        recommendGenre()
    }

    private fun recommendGenre() {
        viewLifecycleOwner.lifecycleScope.launch {
            val ratings = movieService.recommendByGenre()
            Log.d("Recommendation", ratings.toString())
        }
    }

    private fun ratingPercentage() {
        viewLifecycleOwner.lifecycleScope.launch {
            val ratings = service.ratingPercentage()
            Log.d("Recommendation", ratings.toString())
        }
    }
}

