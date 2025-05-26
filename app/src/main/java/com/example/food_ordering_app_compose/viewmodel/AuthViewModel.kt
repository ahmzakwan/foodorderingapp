package com.example.food_ordering_app_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_ordering_app_compose.api.AuthApiService
import com.example.food_ordering_app_compose.api.AuthRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val api: AuthApiService) : ViewModel() {

    private val _authMessage = MutableStateFlow("")
    val authMessage: StateFlow<String> get() = _authMessage

    fun register(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.register(AuthRequest(username, password))
                _authMessage.value = response.body()?.message ?: "Registration failed"
            } catch (e: Exception) {
                _authMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.login(AuthRequest(username, password))
                _authMessage.value = response.body()?.message ?: "Login failed"
            } catch (e: Exception) {
                _authMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}