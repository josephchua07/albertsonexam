package com.example.albertsonexam.data.source

import com.example.albertsonexam.data.models.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {

    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int): Response<RandomUserResponse>

}