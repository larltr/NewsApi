package com.angelika.newsapi.data.remote.apiservice

import com.angelika.newsapi.models.NewsModel
import com.angelika.newsapi.models.NewsResponse
import com.angelika.newsapi.models.SourcesModel
import com.angelika.newsapi.models.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun fetchEverything(
        @Query("q") q: String = "+bitcoin",
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): NewsResponse<NewsModel>

    @GET("top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country") country: String = "ru",
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): NewsResponse<NewsModel>

    @GET("top-headlines/sources")
    suspend fun fetchSources(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): SourcesResponse<SourcesModel>
}