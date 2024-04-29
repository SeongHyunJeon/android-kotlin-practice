package com.example.flightsearch.di

import com.example.flightsearch.data.FlightsPreferencesAccessor
import com.example.flightsearch.data.FlightsPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class FlightsPreferencesRepositoryModule {
    @Binds
    abstract fun bindFlightsPreferencesRepository(impl: FlightsPreferencesRepository): FlightsPreferencesAccessor
}
