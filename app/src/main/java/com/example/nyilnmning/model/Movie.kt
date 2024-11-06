package com.example.nyilnmning.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val genre_ids: List<Int>,
    val runtime: Int,
    val vote_average: Double,
    val popularity: Double,
)
