package com.example.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

interface FlightsPreferencesAccessor {
    suspend fun saveTextPreferences(text: String)
    val currentText: Flow<String>
}

class FlightsPreferencesRepository @Inject constructor(private val dataStore: DataStore<Preferences>): FlightsPreferencesAccessor {

    private companion object {
        val CURRENT_TEXT_KEY = stringPreferencesKey("current_text_key")
        const val TAG = "FlightsPreferencesRepo"
    }

    override suspend fun saveTextPreferences(text: String) {
        dataStore.edit { preferences ->
            preferences[CURRENT_TEXT_KEY] = text
        }
    }

    override val currentText: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { it[CURRENT_TEXT_KEY] ?: "" }
}