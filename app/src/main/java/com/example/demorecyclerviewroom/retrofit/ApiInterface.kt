package com.example.demorecyclerviewroom.retrofit

import com.example.demorecyclerviewroom.User
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    suspend fun getUser(): List<User>
}