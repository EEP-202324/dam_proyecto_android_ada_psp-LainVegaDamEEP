package com.example.teetech.network


import com.example.teetech.model.TShirt
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
"http://10.0.2.2:8080" // esta es la IP del localhost del ordenador
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TShirtApiService {
    @GET("tshirts")
    suspend fun getTShirts(): Response<List<TShirt>>
}

object TShirtApi {
    val retrofitService: TShirtApiService by lazy {
        retrofit.create(TShirtApiService::class.java)
    }
}