package com.angelika.newsapi.models

import com.google.gson.annotations.SerializedName

data class NewsModel(

    @SerializedName("source")
    val source: SourcesModel,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("urlToImage")
    var urlToImage: String,

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("general")
    var general: String
)
