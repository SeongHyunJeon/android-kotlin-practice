package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Airport::class, Favorite::class], version = 4, exportSchema = false)
@TypeConverters(AirportConverter::class)
abstract class FlightDatabase: RoomDatabase() {
    abstract fun airportDao(): AirportDao
    abstract fun favoriteDao(): FavoriteDao
}

class AirportConverter {
    @TypeConverter
    fun fromAirport(airport: Airport): String {
        return "${airport.id},${airport.iataCode},${airport.name},${airport.passengers}"
    }

    @TypeConverter
    fun toAirport(value: String): Airport {
        val parts = value.split(",")
        return Airport(parts[0].toInt(), parts[1], parts[2], parts[3].toInt())
    }
}