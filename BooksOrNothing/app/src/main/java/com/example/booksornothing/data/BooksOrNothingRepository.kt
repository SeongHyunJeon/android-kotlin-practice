package com.example.booksornothing.data

import com.example.booksornothing.network.Books
import com.example.booksornothing.network.BooksOrNothingApiService

interface BooksOrNothingRepository {
    suspend fun getBooks(endpoint: String): Books
}

class NetworkBooksOrNothingRepository(
    private val booksOrNothingApiService: BooksOrNothingApiService
): BooksOrNothingRepository {
    override suspend fun getBooks(endpoint: String): Books = booksOrNothingApiService.getBooks(endpoint)
}