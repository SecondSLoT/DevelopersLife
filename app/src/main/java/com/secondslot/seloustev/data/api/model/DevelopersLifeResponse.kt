package com.secondslot.seloustev.data.api.model

import com.google.gson.annotations.SerializedName

data class DevelopersLifeResponse (
    @SerializedName("result") val result: List<PictureResponse>,
    @SerializedName("totalCount") val totalCount: Int
)