package com.example.flightsearch.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flightsearch.data.AirportDao
import com.example.flightsearch.data.FavoriteDao
import com.example.flightsearch.data.FlightDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FlightDatabase {
        return Room.databaseBuilder(context, FlightDatabase::class.java, "flight_search")
            .createFromAsset("database/flight_search.db")
            .addMigrations(migration1to2)
            .addMigrations(migration2to3)
            .addMigrations(migration3to4)
            .build()
    }

    @Provides
    fun provideAirportDao(database: FlightDatabase): AirportDao {
        return database.airportDao()
    }

    @Provides
    fun provideFavoriteDao(database: FlightDatabase): FavoriteDao {
        return database.favoriteDao()
    }
}