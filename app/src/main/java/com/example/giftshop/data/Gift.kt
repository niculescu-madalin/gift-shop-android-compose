package com.example.giftshop.data

data class Gift(
    val id: Int,
    val name: String,
    val price: Double,
    val imageId: Int, // Or a drawable resource ID if using local images
)