package com.example.giftshop.data

import androidx.compose.runtime.mutableStateListOf

object Cart {
    private val _items = mutableStateListOf<CartItem>()
    val items: List<CartItem> get() = _items

    fun addItem(gift: Gift) {
        val existingItem = _items.find { it.gift.id == gift.id }
        if (existingItem != null) {
            updateItemQuantity(gift, existingItem.quantity + 1)
        } else {
            _items.add(CartItem(gift, 1))
        }
    }

    fun removeItem(gift: Gift) {
        _items.removeAll { it.gift.id == gift.id }
    }

    fun updateItemQuantity(gift: Gift, newQuantity: Int) {
        val existingItem = _items.find { it.gift.id == gift.id }
        if (existingItem != null) {
            if (newQuantity <= 0) {
                removeItem(gift)
            } else {
                val index = _items.indexOf(existingItem)
                if (index != -1) {
                    _items[index] = existingItem.copy(quantity = newQuantity)
                }
            }
        }
    }
    fun clear() {
        _items.clear()
    }
}