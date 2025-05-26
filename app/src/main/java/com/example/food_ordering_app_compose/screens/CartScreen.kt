package com.example.food_ordering_app_compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_ordering_app_compose.api.MenuItemResponse

@Composable
fun CartScreen(cartItems: List<MenuItemResponse>, onCheckout: () -> Unit) {
    val totalPrice = cartItems.sumOf { it.price }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Your Cart", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                Text("${item.name} - $${item.price}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Divider()
        Text("Total: $${totalPrice}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth()) {
            Text("Checkout")
        }
    }
}