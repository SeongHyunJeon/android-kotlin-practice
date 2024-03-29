package com.example.flightsearch.data

interface FlightsAccessor {
    suspend fun getAirport(iataCode: String): Airport
    suspend fun getAvailableAirports(iataCode: String): List<Airport>
    suspend fun getRelatedAirports(text: String): List<Airport>

    suspend fun insert(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun getAllFavorites(): List<Favorite>
}

class FlightsRepository(
    val airportDao: AirportDao,
    val favoriteDao: FavoriteDao
): FlightsAccessor {
    override suspend fun getAirport(iataCode: String): Airport = airportDao.getAirport(iataCode)

    override suspend fun getAvailableAirports(iataCode: String): List<Airport> = airportDao.getAvailableAirports(iataCode)

    override suspend fun getRelatedAirports(text: String): List<Airport> = airportDao.getRelatedAirports(text)

    override suspend fun insert(favorite: Favorite) = favoriteDao.insert(favorite)

    override suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)

    override suspend fun getAllFavorites(): List<Favorite> = favoriteDao.getAllFavorites()
}