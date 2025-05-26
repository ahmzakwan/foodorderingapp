package com.example.food_ordering_app_compose.api

import retrofit2.Response
import retrofit2.http.GET

interface MenuApiService {
    @GET("api/menu/allmenu")
    suspend fun getAllMenuItems(): Response<List<MenuItemResponse>>
}

data class MenuItemResponse(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double
)