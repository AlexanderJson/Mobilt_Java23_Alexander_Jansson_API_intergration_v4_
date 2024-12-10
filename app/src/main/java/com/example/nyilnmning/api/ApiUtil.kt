package com.example.nyilnmning.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiUtil {

    private const val tmbd_URL =  "https://api.themoviedb.org/3/"
    private const val recommendation_URL =  "https://recommendation.eu.pythonanywhere.com"

    @TMBDRetrofit
    @Singleton
    @Provides
    fun launchRetrofitTMBD(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(tmbd_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @RecommendationRetrofit
    @Singleton
    @Provides
    fun launchRetrofitRecommendations(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(recommendation_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(@TMBDRetrofit retrofitTMBD: Retrofit): ApiInterface{
        return retrofitTMBD.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRecommendationInterface(@RecommendationRetrofit retrofitRecommendations: Retrofit): ApiInterfaceRecommendations{
        return retrofitRecommendations.create(ApiInterfaceRecommendations::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TMBDRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RecommendationRetrofit

}