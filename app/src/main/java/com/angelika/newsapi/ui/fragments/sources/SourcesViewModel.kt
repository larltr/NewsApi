package com.angelika.newsapi.ui.fragments.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.angelika.newsapi.data.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val sourcesRepository: NewsRepository
) : ViewModel() {

    fun fetchSources() = sourcesRepository.fetchSources().cachedIn(viewModelScope)
}