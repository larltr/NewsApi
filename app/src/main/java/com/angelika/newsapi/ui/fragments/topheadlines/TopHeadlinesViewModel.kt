package com.angelika.newsapi.ui.fragments.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.angelika.newsapi.data.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val topHeadlinesRepository: NewsRepository
) : ViewModel() {

    fun fetchTopHeadlines() = topHeadlinesRepository.fetchTopHeadlines().cachedIn(viewModelScope)
}