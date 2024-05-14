package com.example.teetech.repository

import com.example.teetech.model.TShirt
import com.example.teetech.network.TShirtApiService
import retrofit2.Response

class TShirtRepository(private val apiService: TShirtApiService) {
    suspend fun getTShirts(): Response<List<TShirt>> = apiService.getTShirts()
    suspend fun createTShirt(tShirt: TShirt): Response<TShirt> = apiService.createTShirt(tShirt)
}

