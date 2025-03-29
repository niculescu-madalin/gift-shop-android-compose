package com.example.giftshop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


/* Page for a single Product  */
@Composable
fun ProductPage(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Product Page")
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPagePreview() {
    ProductPage()
}