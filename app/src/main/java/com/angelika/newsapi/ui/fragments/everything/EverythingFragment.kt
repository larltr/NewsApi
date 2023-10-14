package com.angelika.newsapi.ui.fragments.everything

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.FragmentEverythingBinding
import com.angelika.newsapi.ui.adapters.ListLoadStateAdapter
import com.angelika.newsapi.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EverythingFragment : Fragment(R.layout.fragment_everything) {

    private val binding by viewBinding(FragmentEverythingBinding::bind)
    private val viewModel by viewModels<EverythingViewModel>()
    private val everythingAdapter = NewsAdapter()
    private val everythingLoadStateAdapter = ListLoadStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() = with(binding) {
        val gridLayoutManager = GridLayoutManager(activity, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = everythingAdapter.getItemViewType(position)
                return if (viewType == everythingAdapter.WALLPAPER_VIEW_TYPE) 1
                else 3
            }
        }
        rvEverything.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
        }
        val footerAdapter = everythingAdapter.withLoadStateFooter(everythingLoadStateAdapter)
        rvEverything.adapter = footerAdapter
    }

    private fun setupObserve() {
        viewModel.fetchEverything().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                everythingAdapter.submitData(it)
            }
        }
    }
}