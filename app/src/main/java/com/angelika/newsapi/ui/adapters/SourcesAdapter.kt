package com.angelika.newsapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelika.newsapi.databinding.ItemNewsBinding
import com.angelika.newsapi.models.SourcesModel

class SourcesAdapter :
    PagingDataAdapter<SourcesModel, SourcesAdapter.ViewHolder>(DiffUtilCallback()) {

    private val NETWORK_VIEW_TYPE = 1
    val WALLPAPER_VIEW_TYPE = 2

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(sourcesModel: SourcesModel) = with(binding) {
            binding.tvTitle.text = sourcesModel.name
            binding.tvAuthor.text = sourcesModel.category
            binding.tvName.text = sourcesModel.description
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    companion object {

        class DiffUtilCallback : DiffUtil.ItemCallback<SourcesModel>() {

            override fun areItemsTheSame(oldItem: SourcesModel, newItem: SourcesModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SourcesModel, newItem: SourcesModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            NETWORK_VIEW_TYPE
        } else {
            WALLPAPER_VIEW_TYPE
        }
    }
}