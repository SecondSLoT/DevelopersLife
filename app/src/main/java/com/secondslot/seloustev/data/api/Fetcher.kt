package com.secondslot.seloustev.data.api

import com.secondslot.seloustev.data.api.model.PictureResponse
import com.secondslot.seloustev.data.api.okhttp.PictureRequestInterceptor
import com.secondslot.seloustev.data.api.retrofit.DevelopersLifeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Fetcher"

class Fetcher(category: String, page: Int) {

    private val developersLifeApi: DevelopersLifeApi

    init {

        val client = OkHttpClient.Builder()
            .addInterceptor(PictureRequestInterceptor(category, page))
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://developerslife.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        developersLifeApi = retrofit.create(DevelopersLifeApi::class.java)
    }

    suspend fun fetchPictures(): List<PictureResponse> {
        val developersLifeResponse = developersLifeApi.fetchPicturesMetadata()
        return developersLifeResponse.result
    }
}