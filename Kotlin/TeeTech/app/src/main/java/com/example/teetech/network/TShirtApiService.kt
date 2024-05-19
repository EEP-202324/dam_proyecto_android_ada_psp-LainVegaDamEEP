package com.example.teetech.network


import com.example.teetech.model.TShirt
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path

private const val BASE_URL = "http://192.168.1.252:8080" // esta es la IP del localhost del ordenador
private val retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

interface TShirtApiService {
    @GET("tshirts")
    suspend fun getTShirts(): Response<List<TShirt>>

    @POST("tshirts")
    suspend fun createTShirt(@Body tShirt: TShirt): Response<TShirt>

    @DELETE("tshirts/{id}")
    suspend fun deleteTShirt(@Path("id") id: Long): Response<Unit>  // Asumiendo que tu endpoint solo necesita el ID
}


object TShirtApi {
    val retrofitService: TShirtApiService by lazy {
        retrofit.create(TShirtApiService::class.java)
    }
}