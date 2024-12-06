package com.example.nyilnmning.model

import android.health.connect.datatypes.units.Percentage

data class Recommendations (
    val ratingPercentage: Map<String,String>
)