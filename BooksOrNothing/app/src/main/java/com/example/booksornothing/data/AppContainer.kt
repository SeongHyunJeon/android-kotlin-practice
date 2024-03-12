package com.example.booksornothing.data

import com.example.booksornothing.network.BooksOrNothingApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val booksOrNothingRepository: BooksOrNothingRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: BooksOrNothingApiService by lazy {
        retrofit.create(BooksOrNothingApiService::class.java)
    }

    override val booksOrNothingRepository: BooksOrNothingRepository by lazy {
        NetworkBooksOrNothingRepository(retrofitService)
    }
}