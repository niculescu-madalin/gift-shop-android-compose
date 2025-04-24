package com.example.giftshop.data

data class Order(
    val id: Int = nextOrderId++, //Now id is int and autoincrement
    val items: List<CartItem>,
    val totalPrice: Double
) {
    companion object {
        private var nextOrderId = 1 // Start with an initial ID of 1
    }
}