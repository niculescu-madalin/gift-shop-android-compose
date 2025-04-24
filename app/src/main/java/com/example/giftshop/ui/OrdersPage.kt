package com.example.giftshop.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.giftshop.data.Order
import com.example.giftshop.data.OrderRepository

@Composable
fun OrdersPage() {
    val orders = OrderRepository.orders

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (orders.isEmpty()) {
            Text("You don't have any orders yet", style = MaterialTheme.typography.headlineMedium)
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(orders) { order ->
                    OrderItemCard(order = order)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(order: Order) {
    Column {
        Text(text = "Order ID: ${order.id}", style = MaterialTheme.typography.headlineSmall)
        order.items.forEach { item ->
            Text(text = "${item.gift.name} x${item.quantity}")
        }
        Text(text = "Total: $${order.totalPrice}", style = MaterialTheme.typography.headlineSmall)
    }
}