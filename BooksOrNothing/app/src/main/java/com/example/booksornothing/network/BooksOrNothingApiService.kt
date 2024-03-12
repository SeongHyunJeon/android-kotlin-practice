package com.example.booksornothing.network

import retrofit2.http.GET
import retrofit2.http.Url

interface BooksOrNothingApiService {
    @GET
    suspend fun getBooks(@Url url: String): Books
}