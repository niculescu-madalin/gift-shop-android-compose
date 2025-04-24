package com.example.giftshop.data

import java.util.UUID

data class Order(
    val id: String = UUID.randomUUID().toString(), // Generate a unique ID
    val items: List<CartItem>,
    val totalPrice: Double
)

// Sample Orders Data
val sampleOrders = listOf(
    Order(
        items = listOf(CartItem(sampleGifts[0], 2), CartItem(sampleGifts[2], 1)),
        totalPrice = 90.0
    ),
    Order(
        items = listOf(CartItem(sampleGifts[1], 1), CartItem(sampleGifts[3], 3)),
        totalPrice = 45.0
    ),
)