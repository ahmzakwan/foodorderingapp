package com.example.food_ordering_app_compose.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/register")
    fun register(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/auth/login")
    fun login(@Body request: AuthRequest): Response<AuthResponse>
}

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val message: String
)