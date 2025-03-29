package com.example.giftshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giftshop.R
import com.example.giftshop.data.CartItem
import com.example.giftshop.data.Gift
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import java.text.DecimalFormat

@Composable
fun CartPage(modifier: Modifier = Modifier) {
    val cartItems = remember {
        mutableStateListOf(
            CartItem(Gift(1, "Gift 1", 24.99, R.drawable.gift_generic), 2),
            CartItem(Gift(2, "Gift 2", 19.99, R.drawable.gift_generic), 1),
            CartItem(Gift(3, "Gift 3", 34.99, R.drawable.gift_generic), 3),
        )
    }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f) // Take up available space
        ) {
            items(cartItems) { cartItem ->
                CartItemCard(
                    cartItem = cartItem,
                    onQuantityChange = { newItem ->
                        val index = cartItems.indexOf(cartItem)
                        if (index != -1) {
                            cartItems[index] = newItem
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CartSummary(cartItems = cartItems)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Checkout")
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onQuantityChange: (CartItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cartItem.gift.imageId),
                contentDescription = cartItem.gift.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = cartItem.gift.name, fontWeight = FontWeight.Bold)
                Text(text = "$${cartItem.gift.price}")
            }
            QuantitySelector(cartItem = cartItem, onQuantityChange = onQuantityChange)
        }
    }
}

@Composable
fun QuantitySelector(
    cartItem: CartItem,
    onQuantityChange: (CartItem) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (cartItem.quantity > 1) {
                val newQuantity = cartItem.quantity - 1
                onQuantityChange(cartItem.copy(quantity = newQuantity))
            }
        }) {
            Icon(Icons.Filled.Clear, contentDescription = "Remove")
        }
        Text(text = cartItem.quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
        IconButton(onClick = {
            val newQuantity = cartItem.quantity + 1
            onQuantityChange(cartItem.copy(quantity = newQuantity))
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun CartSummary(cartItems: List<CartItem>) {
    val totalPrice = cartItems.sumOf { it.gift.price * it.quantity }
    val formattedTotal = DecimalFormat("#.##").format(totalPrice)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Total", style = MaterialTheme.typography.headlineSmall)
        Text(text = "$${formattedTotal}", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun CartPagePreview() {
    CartPage()
}