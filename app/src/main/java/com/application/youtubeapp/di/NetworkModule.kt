package com.application.youtubeapp.di

import android.app.Application
import com.application.youtubeapp.BuildConfig
import com.application.youtubeapp.data.api.NetworkInterceptor
import com.application.youtubeapp.data.api.YoutubeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesApiKey(): String {
        return BuildConfig.YOUTUBE_API_KEY
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BuildConfig.YOUTUBE_BASE_URL)
            .addConverterFactory(Json{ ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
    }

    @Provides
    @Singleton
    fun providesOKHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(networkInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesMoviesApi(okHttpClient: OkHttpClient, retrofitBuilder: Retrofit.Builder): YoutubeApi {
        return retrofitBuilder.client(okHttpClient).build().create(YoutubeApi::class.java)
    }

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application) = application.applicationContext


    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}