package com.example.nyilnmning.nn

import java.sql.Date
import java.time.Month
import java.time.Year

data class Preferences(

    val userId: Int,
    val date: Date,
    val month: Month,
    val year: Year,
    val movieId: Int,
    val rating: Double, //0-1
    val averageRating: Double //en femma i betyg av en person är inte lika för en annan

)
