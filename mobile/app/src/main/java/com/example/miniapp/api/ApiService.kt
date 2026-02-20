package com.example.miniapp.api

import com.example.miniapp.model.LoginRequest
import com.example.miniapp.model.RegisterRequest
import com.example.miniapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/login")
    fun login(@Body request: LoginRequest): Call<User>

    @POST("/api/auth/register")
    fun register(@Body request: RegisterRequest): Call<User>
}