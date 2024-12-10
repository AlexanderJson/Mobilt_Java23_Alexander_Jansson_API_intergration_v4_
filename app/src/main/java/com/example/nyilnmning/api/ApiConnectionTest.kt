package com.example.nyilnmning.api

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConnectionTest @Inject constructor(private val api: ApiInterfaceRecommendations){
      suspend fun getPing(): Boolean{
        return try {
            val response = api.ping()
            response.isSuccessful
        } catch (e: Exception){
            Log.e("API", "Not connecting: ${e.message}")
            false
        }
    }
}