package com.example.food_ordering_app_compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_ordering_app_compose.viewmodel.OrderViewModel

@Composable
fun OrderScreen(
    viewModel: OrderViewModel,
    selectedItemIds: List<Int>,
    userEmail: String,
    onOrderPlaced: () -> Unit
) {
    val orderSuccess by viewModel.orderSuccess.collectAsState()

    LaunchedEffect(orderSuccess) {
        if (orderSuccess) onOrderPlaced()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Review and Place Order", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.placeOrder(userEmail, selectedItemIds) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Place Order")
        }
    }
}