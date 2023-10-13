package com.angelika.newsapi.ui.fragments.sources

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.FragmentSourcesBinding
import com.angelika.newsapi.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourcesFragment : Fragment(R.layout.fragment_sources) {

    private val binding by viewBinding(FragmentSourcesBinding::bind)
    private val sourcesAdapter = NewsAdapter()
    private val viewModel by viewModels<SourcesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() = with(binding) {
        rvSources.adapter = sourcesAdapter
    }

    private fun setupObserve() {
        viewModel.fetchSources().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                sourcesAdapter.submitData(it)
            }
        }
    }
}