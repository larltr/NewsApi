package com.angelika.newsapi.models

import com.google.gson.annotations.SerializedName

data class NewsResponse<T>(

    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<T>
)