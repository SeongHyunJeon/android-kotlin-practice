package com.example.booksornothing

import android.app.Application
import com.example.booksornothing.data.AppContainer
import com.example.booksornothing.data.DefaultAppContainer

class BooksOrNothingApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}