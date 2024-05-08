package com.example.teetech.network

import com.example.teetech.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"  // Cambia esta URL por la de tu backend

    // Lazy initialization de Retrofit para asegurarnos de que solo se crea una vez
    val instance: ApiService by lazy {
        // Configura Retrofit para usar Gson como su convertidor de JSON
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Retorna una implementación de la API creada automáticamente por Retrofit
        retrofit.create(ApiService::class.java)
    }
}
