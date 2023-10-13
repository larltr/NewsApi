package com.angelika.newsapi.models

import com.google.gson.annotations.SerializedName

data class SourcesModel(

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
