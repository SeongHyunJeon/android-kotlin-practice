package com.gettingold.firebase.data.remote.di

import com.gettingold.firebase.data.remote.repository.UserRegistrationAccessor
import com.gettingold.firebase.data.remote.repository.UserRegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserRegistrationRepositoryModule {
    @Binds
    abstract fun bindUserRegistrationAccessor(impl: UserRegistrationRepository): UserRegistrationAccessor
}