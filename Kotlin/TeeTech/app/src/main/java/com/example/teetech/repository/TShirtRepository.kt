package com.example.teetech.repository

import com.example.teetech.network.TShirtApiService
import com.example.teetech.model.TShirt
import retrofit2.Response

class TShirtRepository(private val apiService: TShirtApiService) {
    suspend fun getTShirts(): Response<List<TShirt>> = apiService.getTShirts()
}
