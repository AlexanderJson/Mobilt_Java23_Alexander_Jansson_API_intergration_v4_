package com.example.nyilnmning.viewmodel

import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.frontpage.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class AppModule {


    @Singleton
    @Provides
    fun provideRepository(apiInterface: ApiInterface): MovieRepository {
        return MovieRepository(apiInterface)
    }


    @Singleton
    @Provides
    fun provideString(): String {
        return "Works!"
    }
}