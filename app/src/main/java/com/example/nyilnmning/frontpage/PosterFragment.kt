package com.example.nyilnmning.frontpage

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
import com.example.nyilnmning.viewmodel.PosterImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PosterFragment :  Fragment() {

    @Inject
    lateinit var adapter: FrontPageAdapter
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
            vm.trendingFrontPage.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}