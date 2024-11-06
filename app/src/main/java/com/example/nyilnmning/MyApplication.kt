package com.example.nyilnmning

import android.app.Application
import com.example.nyilnmning.viewmodel.AppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
