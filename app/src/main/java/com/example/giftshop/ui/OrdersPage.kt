package com.example.giftshop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OrdersPage(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Orders Page")
    }
}

@Preview(showBackground = true)
@Composable
fun OrdersPagePreview() {
    OrdersPage()
}