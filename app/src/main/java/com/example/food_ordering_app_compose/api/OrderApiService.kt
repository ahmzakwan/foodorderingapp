package com.example.food_ordering_app_compose.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApiService {
    @POST("/api/orders")
    suspend fun placeOrder(@Body orderRequest: OrderRequest): Response<Unit>
}

data class OrderRequest(
    val userEmail: String,
    val items: List<Int> // List of MenuItem IDs
)