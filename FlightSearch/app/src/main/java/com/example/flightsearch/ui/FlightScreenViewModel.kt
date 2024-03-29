package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightsAccessor
import com.example.flightsearch.data.FlightsPreferencesAccessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightScreenViewModel(
    private val flightsAccessor: FlightsAccessor,
    private val flightsPreferencesAccessor: FlightsPreferencesAccessor
): ViewModel() {
    private val _uiState = MutableStateFlow(FlightUiState())
    val uiState: StateFlow<FlightUiState> = _uiState

    init {
        initialiseFlight()
    }

    private fun initialiseFlight() {
        viewModelScope.launch {
            _uiState.update { flightUiState ->
                flightUiState.copy(
                    currentText = flightsPreferencesAccessor.currentText.first()
                )
            }
            updateFavoriteAirports()
            updateRelatedAirports(_uiState.value.currentText)
        }
    }

    fun updateCurrentText(text: String) {
        _uiState.update { flightUiState ->
            flightUiState.copy(
                currentText = text,
            )
        }

        viewModelScope.launch {
            flightsPreferencesAccessor.saveTextPreferences(text)
        }
    }

    fun updateSelectedAirport(airport: Airport?) {
        _uiState.update { flightUiState ->
            flightUiState.copy(selectedAirport = airport)
        }
    }

    fun updateRelatedAirports(text: String) {
        viewModelScope.launch {
            _uiState.update { flightUiState ->
                flightUiState.copy(
                    relatedAirports = flightsAccessor.getRelatedAirports(text)
                )
            }
        }
    }

    fun updateAvailableAirports(iataCode: String) {
        viewModelScope.launch {
            _uiState.update { flightUiState ->
                flightUiState.copy(availableAirports = flightsAccessor.getAvailableAirports(iataCode))
            }
        }
    }

    private fun updateFavoriteAirports() {
        viewModelScope.launch {
            _uiState.update { flightUiState ->
                flightUiState.copy(favoriteAirports = flightsAccessor.getAllFavorites())
            }
        }
    }

    fun insertFavoriteAirport(favorite: Favorite) {
        viewModelScope.launch {
            flightsAccessor.insert(favorite)
            updateFavoriteAirports()
        }
    }

    fun deleteFavoriteAirport(favorite: Favorite) {
        viewModelScope.launch {
            flightsAccessor.delete(favorite)
            updateFavoriteAirports()
        }
    }

    fun isFavorite(favorite: Favorite): Boolean =
        _uiState.value.favoriteAirports.contains(favorite)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as FlightApplication
                FlightScreenViewModel(
                    application.container.flightsAccessor,
                    application.container.flightsPreferencesAccessor
                    )
            }
        }
    }
}

fun Airport.toFavorite(airport: Airport) =
    Favorite(id = "${this.iataCode}_${airport.iataCode}", departureAirport = this, destinationAirport = airport)

data class FlightUiState(
    val currentText: String = "", //현재 입력된 텍스트.
    val selectedAirport: Airport? = null, //현재 선택된 공항.
    val relatedAirports: List<Airport> = emptyList(), //공항 추천 목록.
    val availableAirports: List<Airport> = emptyList(), //출국 가능한 공항.
    val favoriteAirports: List<Favorite> = emptyList() //자주 이용하는 항공편.
)