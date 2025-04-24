package com.example.giftshop.data

import com.example.giftshop.R

data class Gift(
    val id: Int,
    val name: String,
    val price: Double,
    val imageId: Int, // Or a drawable resource ID if using local images
)

// Sample Gifts Data
val sampleGifts = listOf(
    Gift(1, "Teddy Bear", 25.00, R.drawable.teddy_bear),
    Gift(2, "Chocolate Box", 15.00, R.drawable.chocolate_box),
    Gift(3, "Perfume", 40.00, R.drawable.perfume),
    Gift(4, "Book", 10.00, R.drawable.book),
    Gift(5, "Jewelry", 70.00, R.drawable.jewelry),
    Gift(6, "Gift", 50.00, R.drawable.gift_generic)
)