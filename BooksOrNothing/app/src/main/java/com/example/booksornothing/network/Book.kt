package com.example.booksornothing.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val totalItems: Int = 0,
    val items: List<Book>
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val title: String = "",
    val authors: List<String> = listOf("No Data"),
    val publisher: String = "",
    val publishedDate: String = "",
    val description: String = "There's no description.",
    val pageCount: Int = 0,
    val categories: List<String> = listOf("No Data"),
    val imageLinks: ImageLinks = ImageLinks(),
)

@Serializable
data class ImageLinks(
    val thumbnail: String = ""
)