package com.example.giftshop.data

data class Gift(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String, // Or a drawable resource ID if using local images
)