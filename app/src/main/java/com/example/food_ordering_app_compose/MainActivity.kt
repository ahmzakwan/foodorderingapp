package com.example.food_ordering_app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.food_ordering_app_compose.api.AuthApiService
import com.example.food_ordering_app_compose.api.MenuApiService
import com.example.food_ordering_app_compose.api.OrderApiService
import com.example.food_ordering_app_compose.screens.LoginScreen
import com.example.food_ordering_app_compose.screens.AuthScreen
import com.example.food_ordering_app_compose.screens.CartScreen
import com.example.food_ordering_app_compose.screens.MenuScreen
import com.example.food_ordering_app_compose.screens.OrderScreen
import com.example.food_ordering_app_compose.viewmodel.AuthViewModel
import com.example.food_ordering_app_compose.viewmodel.MenuViewModel
import com.example.food_ordering_app_compose.viewmodel.OrderViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // Emulator local server
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val menuApi = retrofit.create(MenuApiService::class.java)
        val orderApi = retrofit.create(OrderApiService::class.java)
        val authApi = retrofit.create(AuthApiService::class.java)

        val menuViewModel = MenuViewModel(menuApi)
        val orderViewModel = OrderViewModel(orderApi)
        val authViewModel = AuthViewModel(authApi)

//        setContent {
//            var username by remember { mutableStateOf<String?>(null) }
//            var password by remember{ mutableStateOf<String?>(null) }
//            var cart by remember { mutableStateOf(listOf<com.example.food_ordering_app_compose.api.MenuItemResponse>()) }
//
//            when {
//                username == null -> LoginScreen(authViewModel = authViewModel, onLoginSuccess = { loggedInUsername ->
//                    username = loggedInUsername
//                } )
//                cart.isEmpty() -> MenuScreen(
//                    viewModel = menuViewModel,
//                    onAddToCart = { cart = cart + it }
//                )
////                else -> CartScreen(
////                    cartItems = cart,
////                    onCheckout = {
////                        OrderScreen(
////                            viewModel = orderViewModel,
////                            selectedItemIds = cart.map { it.id },
////                            userEmail = userEmail!!,
////                            onOrderPlaced = {
////                                cart = emptyList() // Clear cart on success
////                                menuViewModel.fetchMenu() // Reload menu
////                            }
////                        )
////                    }
////                )
//            }
//        }
        setContent {
            var username by remember { mutableStateOf<String?>(null) }
            var password by remember { mutableStateOf<String?>(null) }
            var cart by remember { mutableStateOf(listOf<com.example.food_ordering_app_compose.api.MenuItemResponse>()) }

            // Show login if username or password is null or empty
            if (username.isNullOrBlank() || password.isNullOrBlank()) {
                LoginScreen(authViewModel = authViewModel, onLoginSuccess = { u, p ->
                    username = u
                    password = p
                })
            } else if (cart.isEmpty()) {
                MenuScreen(
                    viewModel = menuViewModel,
                    onAddToCart = { cart = cart + it }
                )
            }
            // else show CartScreen or other screen as needed
        }
    }
}
//    }
//}