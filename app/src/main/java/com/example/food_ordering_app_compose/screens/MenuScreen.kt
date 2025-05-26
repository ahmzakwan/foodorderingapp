package com.example.food_ordering_app_compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_ordering_app_compose.api.MenuItemResponse
import com.example.food_ordering_app_compose.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    onAddToCart: (MenuItemResponse) -> Unit
) {
    val menuItems by viewModel.menuItems.collectAsState()

    // Example UI rendering items
    LazyColumn {
        items(menuItems) { item ->
            MenuItemCard(item = item, onAddToCart = onAddToCart)
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItemResponse,
    onAddToCart: (MenuItemResponse) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "$${item.price}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onAddToCart(item) }) {
                Text("Add to Cart")
            }
        }
    }
}

