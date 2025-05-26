package com.example.food_ordering_app_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_ordering_app_compose.api.MenuApiService
import com.example.food_ordering_app_compose.api.MenuItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel(private val api: MenuApiService) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItemResponse>>(emptyList())
    val menuItems: StateFlow<List<MenuItemResponse>> get() = _menuItems

    fun fetchMenu() {
        viewModelScope.launch {
            try {
                val response = api.getAllMenuItems()
                if (response.isSuccessful) {
                    _menuItems.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                _menuItems.value = emptyList()
            }
        }
    }
}