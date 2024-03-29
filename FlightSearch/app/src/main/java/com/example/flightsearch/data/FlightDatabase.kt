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

    companion object {
        @Volatile
        private var INSTANCE: FlightDatabase? = null

        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE new_favorite (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, departureAirport TEXT NOT NULL, destinationAirport TEXT NOT NULL)")
                database.execSQL("DROP TABLE favorite")
                database.execSQL("ALTER TABLE new_favorite RENAME TO favorite")
            }
        }

        val migration2to3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE new_favorite (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, departure_airport TEXT NOT NULL, destination_airport TEXT NOT NULL)")
                database.execSQL("DROP TABLE favorite")
                database.execSQL("ALTER TABLE new_favorite RENAME TO favorite")
            }
        }

        val migration3to4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE new_favorite (id TEXT PRIMARY KEY NOT NULL, departure_airport TEXT NOT NULL, destination_airport TEXT NOT NULL)")
                database.execSQL("DROP TABLE favorite")
                database.execSQL("ALTER TABLE new_favorite RENAME TO favorite")
            }
        }

        fun getDatabase(context: Context): FlightDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDatabase::class.java, "flight_search")
                    .createFromAsset("database/flight_search.db")
                    .addMigrations(migration1to2)
                    .addMigrations(migration2to3)
                    .addMigrations(migration3to4)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
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