package com.example.nyilnmning.adapter

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nyilnmning.model.Movie
import javax.inject.Inject

class FrontPageAdapter @Inject constructor() :
    PagingDataAdapter<Movie, FrontPageAdapter.MovieViewHolder>(diffUtilCallback()) {


    class MovieViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        Glide.with(holder.imageView.context)
            .load("https://image.tmdb.org/t/p/w500${movie?.poster_path}")
            .into(holder.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return MovieViewHolder(imageView)
    }

    fun likeMovie(position: Int): Movie? {
        val movie = getItem(position)
        return if (movie != null) {
            movie
        } else {
            Log.d("MovieLike", "No movie found to like")
            null
        }
    }
//        return if (movie != null){
//            mapOf(
//                "movieID" to movie.id,
//                "genreIDs" to movie.genre_ids.getOrNull(0),
//                "voteAVG" to movie.vote_average,
//                "popularity" to movie.popularity
//            )



    class diffUtilCallback : DiffUtil.ItemCallback<Movie>() {  // diffUtil calculates redraws in UI to only redraw what is needed, good to save memory for managing reloading etc. Since it doesnt redraw content that already exists

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id //compares only id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem //compare whole objects
        }
    }

}

