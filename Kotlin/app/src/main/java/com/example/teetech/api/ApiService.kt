package com.example.teetech.api

import com.example.teetech.TShirt
import com.example.teetech.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    // Métodos para Users
    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Long, @Body user: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Long): Call<Void>

    // Métodos para TShirts
    @GET("tshirts")
    fun getAllTShirts(): Call<List<TShirt>>

    @POST("tshirts")
    fun createTShirt(@Body tShirt: TShirt): Call<TShirt>

    @PUT("tshirts/{id}")
    fun updateTShirt(@Path("id") id: Long, @Body tShirt: TShirt): Call<TShirt>

    @DELETE("tshirts/{id}")
    fun deleteTShirt(@Path("id") id: Long): Call<Void>
}
