package com.example.newsapp.data.remote.dto

import com.example.newsapp.ultils.Constants
import retrofit2.http.Query
import retrofit2.http.GET

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}