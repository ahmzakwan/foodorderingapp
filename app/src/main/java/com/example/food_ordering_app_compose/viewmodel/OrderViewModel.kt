package com.example.food_ordering_app_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_ordering_app_compose.api.OrderApiService
import com.example.food_ordering_app_compose.api.OrderRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(private val api: OrderApiService) : ViewModel() {

    private val _orderSuccess = MutableStateFlow(false)
    val orderSuccess: StateFlow<Boolean> get() = _orderSuccess

    fun placeOrder(userEmail: String, menuItemIds: List<Int>) {
        val request = OrderRequest(userEmail, menuItemIds)
        viewModelScope.launch {
            try {
                val response = api.placeOrder(request)
                _orderSuccess.value = response.isSuccessful
            } catch (e: Exception) {
                _orderSuccess.value = false
            }
        }
    }
}