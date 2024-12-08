package com.example.nyilnmning.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.nyilnmning.R
import com.example.nyilnmning.adapter.ViewpageAdapter
import com.example.nyilnmning.viewmodel.PosterImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PosterFragment :  Fragment() {

    @Inject
    lateinit var adapter: ViewpageAdapter
    val vm: PosterImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_poster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        frontPageTrending()
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun frontPageTrending() {
        viewLifecycleOwner.lifecycleScope.launch {

            // init a request that adds movie data to LiveData list
            vm.getTrending()

            // create movi
            vm.movies.observe(viewLifecycleOwner) { movies ->
                movies?.let {
                    val urls = it.map { movie ->
                        "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                    }
                    adapter.setImages(urls)
                }
            }
        }
    }
}