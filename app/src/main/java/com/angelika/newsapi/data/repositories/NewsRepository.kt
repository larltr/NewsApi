package com.angelika.newsapi.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.angelika.newsapi.data.remote.apiservice.NewsApiService
import com.angelika.newsapi.data.remote.pagingsources.EverythingPagingSource
import com.angelika.newsapi.data.remote.pagingsources.SourcesPagingSource
import com.angelika.newsapi.data.remote.pagingsources.TopHeadlinesPagingSource
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {

    fun fetchEverything() = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        EverythingPagingSource(newsApiService)
    }.liveData

    fun fetchTopHeadlines() = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        TopHeadlinesPagingSource(newsApiService)
    }.liveData

    fun fetchSources() = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        SourcesPagingSource(newsApiService)
    }.liveData
}