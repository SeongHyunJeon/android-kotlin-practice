package com.gettingold.firebase.data.remote.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun realtimeDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun databaseReference(database: FirebaseDatabase): DatabaseReference = database.reference
}