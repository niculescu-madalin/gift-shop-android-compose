package com.example.giftshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giftshop.R
import com.example.giftshop.data.Gift
import com.example.giftshop.data.Order
import com.example.giftshop.data.OrderItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun OrdersPage(modifier: Modifier = Modifier) {
    val orders = listOf(
        Order(
            id = 1,
            date = Date(),
            total = 74.97,
            items = listOf(
                OrderItem(Gift(1, "Gift 1", 24.99, R.drawable.gift_generic), 1),
                OrderItem(Gift(2, "Gift 2", 19.99, R.drawable.gift_generic), 2)
            )
        ),
        Order(
            id = 2,
            date = Date(),
            total = 104.98,
            items = listOf(
                OrderItem(Gift(3, "Gift 3", 34.99, R.drawable.gift_generic), 1),
                OrderItem(Gift(4, "Gift 4", 14.99, R.drawable.gift_generic), 3)
            )
        ),
    )

    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn {
            items(orders) { order ->
                OrderCard(order = order)
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Order #${order.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = formatDate(order.date),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Text(
                    text = "Total:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$${"%.2f".format(order.total)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            order.items.take(3).forEach { orderItem ->
                OrderItemRow(orderItem = orderItem)
            }
        }
    }
}

@Composable
fun OrderItemRow(orderItem: OrderItem) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = orderItem.gift.imageId),
            contentDescription = orderItem.gift.name,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = orderItem.gift.name)
            Text(text = "Quantity: ${orderItem.quantity}")
        }
    }
}

fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}

@Preview(showBackground = true)
@Composable
fun OrdersPagePreview() {
    OrdersPage()
}