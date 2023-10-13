package com.angelika.newsapi.ui.fragments.everything

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.angelika.newsapi.data.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    private val everythingRepository: NewsRepository
) : ViewModel() {

    fun fetchEverything() = everythingRepository.fetchEverything().cachedIn(viewModelScope)
}

