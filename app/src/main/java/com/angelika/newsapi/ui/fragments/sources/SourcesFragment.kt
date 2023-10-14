package com.angelika.newsapi.ui.fragments.sources

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.FragmentSourcesBinding
import com.angelika.newsapi.ui.adapters.ListLoadStateAdapter
import com.angelika.newsapi.ui.adapters.SourcesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourcesFragment : Fragment(R.layout.fragment_sources) {

    private val binding by viewBinding(FragmentSourcesBinding::bind)
    private val viewModel by viewModels<SourcesViewModel>()
    private val sourcesAdapter = SourcesAdapter()
    private val sourcesLoadStateAdapter = ListLoadStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() = with(binding) {
        val gridLayoutManager = GridLayoutManager(activity, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = sourcesAdapter.getItemViewType(position)
                return if (viewType == sourcesAdapter.WALLPAPER_VIEW_TYPE) 1
                else 3
            }
        }
        rvSources.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
        }
        val footerAdapter = sourcesAdapter.withLoadStateFooter(sourcesLoadStateAdapter)
        rvSources.adapter = footerAdapter
    }

    private fun setupObserve() {
        viewModel.fetchSources().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                sourcesAdapter.submitData(it)
            }
        }
    }
}