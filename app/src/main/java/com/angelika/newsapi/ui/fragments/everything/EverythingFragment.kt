package com.angelika.newsapi.ui.fragments.everything

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.FragmentEverythingBinding
import com.angelika.newsapi.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EverythingFragment : Fragment(R.layout.fragment_everything) {

    private val binding by viewBinding(FragmentEverythingBinding::bind)
    private val viewModel by viewModels<EverythingViewModel>()
    private val everythingAdapter = NewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() = with(binding) {
        rvEverything.adapter = everythingAdapter
    }

    private fun setupObserve() {
        viewModel.fetchEverything().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                everythingAdapter.submitData(it)
            }
        }
    }
}