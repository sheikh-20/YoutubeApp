package com.application.youtubeapp.di

import android.content.Context
import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.repository.AuthRepo
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesYoutubeRepo(youtubeApi: YoutubeApi, coroutineScope: CoroutineScope) = YoutubeRepository(youtubeApi, coroutineScope)

    @Provides
    @Singleton
    fun providesAuthRepo(context: Context, firebaseAuth: FirebaseAuth) = AuthRepo(context, firebaseAuth)
}