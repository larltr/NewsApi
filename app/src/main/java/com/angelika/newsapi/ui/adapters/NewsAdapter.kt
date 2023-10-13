package com.angelika.newsapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelika.newsapi.R
import com.angelika.newsapi.databinding.ItemNewsBinding
import com.angelika.newsapi.models.NewsModel
import com.bumptech.glide.Glide

class NewsAdapter : PagingDataAdapter<NewsModel, NewsAdapter.ViewHolder>(DiffUtilCallBack()) {

    private val NETWORK_VIEW_TYPE = 1
    val WALLPAPER_VIEW_TYPE = 2

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(newsModel: NewsModel) = with(binding) {
            binding.tvTitle.text = newsModel.title
            binding.tvAuthor.text = newsModel.author
            binding.tvName.text = newsModel.source.name
            Glide.with(binding.ivUrlImage).load(newsModel.urlToImage).into(binding.ivUrlImage)
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
                ),
                parent, false
            )
        )
    }

    companion object {
        class DiffUtilCallBack : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(
                oldItem: NewsModel,
                newItem: NewsModel
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: NewsModel,
                newItem: NewsModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}