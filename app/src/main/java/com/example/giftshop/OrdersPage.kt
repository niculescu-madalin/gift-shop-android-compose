package com.example.giftshop

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OrdersPage(modifier: Modifier = Modifier) {
    Text(text = "Products Page")
}

@Preview(showBackground = true)
@Composable
fun OrdersPagePreview() {
    OrdersPage()
}