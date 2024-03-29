package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface AppContainer {
    val flightsAccessor: FlightsAccessor
    val flightsPreferencesAccessor: FlightsPreferencesAccessor
}

class AppDataContainer(
    private val context: Context,
    private val dataStore: DataStore<Preferences>
): AppContainer {
    override val flightsAccessor: FlightsAccessor by lazy {
        val database = FlightDatabase.getDatabase(context)
        FlightsRepository(database.airportDao(), database.favoriteDao())
    }
    override val flightsPreferencesAccessor: FlightsPreferencesAccessor by lazy {
        FlightsPreferencesRepository(dataStore)
    }
}
