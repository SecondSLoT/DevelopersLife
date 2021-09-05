package com.secondslot.seloustev.data.api.model

import com.google.gson.annotations.SerializedName

data class PictureResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("author") val author: String,
        @SerializedName("canVote") val canVote: Boolean,
        @SerializedName("commentsCount") val commentsCount: Int,
        @SerializedName("date") val date: String,
        @SerializedName("description") val description: String,
        @SerializedName("gifURL") val gifURL: String,
        @SerializedName("previewURL") val previewURL: String,
        @SerializedName("votes") val votes: Int
)
