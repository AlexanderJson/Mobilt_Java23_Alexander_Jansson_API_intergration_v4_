package com.example.nyilnmning

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
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.nyilnmning.repository.MovieRepository
import com.example.nyilnmning.service.DisplayService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RateMovieFragment :  Fragment() {


    @Inject
    lateinit var repository: MovieRepository


    @Inject
    lateinit var service: DisplayService



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_controller, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val likeBtn = view.findViewById<Button>(R.id.likeBtn)
        likeBtn.setOnClickListener{
            getTrending(view)
        }
        val dislikeBtn = view.findViewById<Button>(R.id.dislikeBtn)
        dislikeBtn.setOnClickListener{

        }





    }
    private fun reviewMovie(view: View){

    }

}