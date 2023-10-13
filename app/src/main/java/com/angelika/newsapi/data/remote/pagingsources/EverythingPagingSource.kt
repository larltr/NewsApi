package com.angelika.newsapi.data.remote.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angelika.newsapi.data.remote.apiservice.NewsApiService
import com.angelika.newsapi.models.NewsModel
import retrofit2.HttpException
import java.io.IOException

class EverythingPagingSource(
    private val everythingApiService: NewsApiService
) : PagingSource<Int, NewsModel>() {

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        val pageSize = params.loadSize
        val page = params.key ?: 1
        return try {
            val response = everythingApiService.fetchEverything(pageSize = pageSize, page = page)
            val nextPageNumber = page + 1
            val prevPageNumber = page - 1
            LoadResult.Page(
                response.articles,
                prevPageNumber,
                nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}