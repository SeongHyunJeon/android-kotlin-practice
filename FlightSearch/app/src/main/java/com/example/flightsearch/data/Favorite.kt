package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo(name = "departure_airport")
    val departureAirport: Airport,
    @ColumnInfo(name = "destination_airport")
    val destinationAirport: Airport
)
