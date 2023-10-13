package com.angelika.newsapi.di

import com.angelika.newsapi.data.remote.RetrofitClient
import com.angelika.newsapi.data.remote.apiservice.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient: RetrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideNewsApiService(): NewsApiService {
        return retrofitClient.newApi
    }
}

