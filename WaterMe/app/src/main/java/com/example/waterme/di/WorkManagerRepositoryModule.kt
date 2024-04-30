package com.example.waterme.di

import com.example.waterme.data.WaterRepository
import com.example.waterme.data.WorkManagerWaterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class WorkManagerRepositoryModule {
    @Binds
    abstract fun bindWaterRepository(impl: WorkManagerWaterRepository): WaterRepository
}