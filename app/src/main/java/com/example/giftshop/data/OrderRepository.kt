package com.example.giftshop.data

object OrderRepository {
    private val _orders = mutableListOf<Order>()
    val orders: List<Order> get() = _orders.toList()

    fun addOrder(order: Order) {
        _orders.add(order)
    }
}