package com.axel.healthjoychallenge.data.datasource.rest

import com.axel.healthjoychallenge.data.datasource.rest.response.RestPostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getPost(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") pageSize: Int,
        @Query("offset") page: Int  // start from zero
    ): Response<RestPostResponse>
}