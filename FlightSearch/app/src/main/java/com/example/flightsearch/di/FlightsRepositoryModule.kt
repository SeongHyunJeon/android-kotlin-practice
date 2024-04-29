package com.example.flightsearch.di

import com.example.flightsearch.data.FlightsAccessor
import com.example.flightsearch.data.FlightsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class FlightsRepositoryModule {
    @Binds
    abstract fun bindFlightsRepository(impl: FlightsRepository): FlightsAccessor
}