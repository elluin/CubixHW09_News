package com.example.cubixhw09_news.network

import com.example.cubixhw09_news.data.NewsResult
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=hu&apiKey=3337a896861a4234bf1ca9f2bab6f4ba <- enyém

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getNews(@Query("country") country: String,
                        @Query("apiKey") apiKey: String): NewsResult

}