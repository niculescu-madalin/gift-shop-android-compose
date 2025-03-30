package com.example.giftshop.data

import java.util.Date

data class Order(
    val id: Int,
    val date: Date,
    val total: Double,
    val items: List<OrderItem>
)

data class OrderItem(
    val gift: Gift,
    val quantity: Int
)