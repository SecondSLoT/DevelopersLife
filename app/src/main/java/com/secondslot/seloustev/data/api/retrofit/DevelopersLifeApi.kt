package com.secondslot.seloustev.data.api.retrofit

import com.secondslot.seloustev.data.api.model.DevelopersLifeResponse
import retrofit2.http.GET

interface DevelopersLifeApi {

    @GET("/")
    suspend fun fetchPicturesMetadata(): DevelopersLifeResponse
}