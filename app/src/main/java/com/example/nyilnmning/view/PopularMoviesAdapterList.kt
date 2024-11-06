package com.example.nyilnmning.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nyilnmning.R
import com.example.nyilnmning.model.Movie


class PopularMoviesAdapterList(private var movieList: List<Movie>) : RecyclerView.Adapter<PopularMoviesAdapterList.MovieViewHolder>() {

    // hämtar och sätter alla itemViews med default värden
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.label_title)
        val overview: TextView = itemView.findViewById(R.id.label_overview)
        val runtime: TextView = itemView.findViewById(R.id.label_runtime)
        val genre: TextView = itemView.findViewById(R.id.label_genre)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    // binder data till itemViews i listan
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview
        holder.runtime.text = movie.runtime.toString()
        holder.genre.text = movie.genre_ids.toString()
    }

    // storlek på lista
    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovies(newMovies: List<Movie>){
        movieList = newMovies
        notifyDataSetChanged()
    }
}
