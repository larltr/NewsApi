package com.angelika.newsapi.ui.fragments.topheadlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.FragmentTopHeadlinesBinding
import com.angelika.newsapi.ui.adapters.ListLoadStateAdapter
import com.angelika.newsapi.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment(R.layout.fragment_top_headlines) {

    private val binding by viewBinding(FragmentTopHeadlinesBinding::bind)
    private val viewModel by viewModels<TopHeadlinesViewModel>()
    private val topHeadlinesAdapter = NewsAdapter()
    private val topHeadlinesLoadStateAdapter = ListLoadStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() = with(binding) {
        val gridLayoutManager = GridLayoutManager(activity, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = topHeadlinesAdapter.getItemViewType(position)
                return if (viewType == topHeadlinesAdapter.WALLPAPER_VIEW_TYPE) 1
                else 3
            }
        }
        rvTopHeadlines.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
        }
        val footerAdapter = topHeadlinesAdapter.withLoadStateFooter(topHeadlinesLoadStateAdapter)
        rvTopHeadlines.adapter = footerAdapter
    }

    private fun setupObserve() {
        viewModel.fetchTopHeadlines().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                topHeadlinesAdapter.submitData(it)
            }
        }
    }
}