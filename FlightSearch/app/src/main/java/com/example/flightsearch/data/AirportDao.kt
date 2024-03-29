package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("""
        SELECT * FROM airport
        WHERE name LIKE '%' || :text || '%' OR iata_code LIKE '%' || :text || '%'
       """)
    suspend fun getRelatedAirports(text: String): List<Airport>

    @Query("""
        SELECT * FROM airport
        WHERE iata_code != :iataCode
    """)
    suspend fun getAvailableAirports(iataCode: String): List<Airport>

    @Query("""
        SELECT * FROM airport
        WHERE iata_code = :iataCode
    """)
    suspend fun getAirport(iataCode: String): Airport
}