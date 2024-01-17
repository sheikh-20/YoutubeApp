package com.application.youtubeapp.di

import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.usecase.ChannelInfoUseCase
import com.application.youtubeapp.domain.usecase.GetChannelInfoInteractor
import com.application.youtubeapp.domain.usecase.GetVideoCategoryInteractor
import com.application.youtubeapp.domain.usecase.GetVideoDetailInteractor
import com.application.youtubeapp.domain.usecase.GetVideoPopularInteractor
import com.application.youtubeapp.domain.usecase.VideoCategoryUseCase
import com.application.youtubeapp.domain.usecase.VideoDetailUseCase
import com.application.youtubeapp.domain.usecase.VideoPopularUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesVideoCategoryUseCase(youtubeRepository: YoutubeRepository): VideoCategoryUseCase {
        return GetVideoCategoryInteractor(youtubeRepository)
    }

    @Provides
    @Singleton
    fun providesVideoPopularUseCase(youtubeRepository: YoutubeRepository): VideoPopularUseCase {
        return GetVideoPopularInteractor(youtubeRepository)
    }

    @Provides
    @Singleton
    fun providesChannelInfoUseCase(youtubeRepository: YoutubeRepository): ChannelInfoUseCase {
        return GetChannelInfoInteractor(youtubeRepository);
    }

    @Provides
    @Singleton
    fun providesVideoDetailUseCase(youtubeRepository: YoutubeRepository): VideoDetailUseCase {
        return GetVideoDetailInteractor(youtubeRepository)
    }
}