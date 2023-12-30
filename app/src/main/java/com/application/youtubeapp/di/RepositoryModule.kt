package com.application.youtubeapp.di

import com.application.youtubeapp.data.repository.YoutubeRepositoryImpl
import com.application.youtubeapp.data.repository.YoutubeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesYoutubeRepositoryImpl(youtubeRepositoryImpl: YoutubeRepositoryImpl): YoutubeRepository
}