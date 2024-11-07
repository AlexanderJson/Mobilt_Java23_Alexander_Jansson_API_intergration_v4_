package com.example.nyilnmning.nn

import java.sql.Date
import java.time.Month
import java.time.Year

data class InputData(

    val genreId: Int,
    val voteAverage: Double,
    val popularity: Double,
    val movieId: Int,
    val userId: Int,
    val date: Date,
    val month: Month,
    val year: Year,
    val rating: Double, //0-1
)
