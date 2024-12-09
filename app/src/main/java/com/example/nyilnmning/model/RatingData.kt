package com.example.nyilnmning.model

data class RatingData (
    val filmID: Int?,
    val genreID: Int?,
    val userID: Int,
    val rating: Float = 5.0f
)