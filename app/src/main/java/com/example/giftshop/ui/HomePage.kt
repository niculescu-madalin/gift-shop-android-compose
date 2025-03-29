package com.example.giftshop.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giftshop.R
import com.example.giftshop.data.Gift

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onGiftClick: (Gift) -> Unit
) {
    val gifts = listOf(
        Gift(1, "Gift 1", 24.99, R.drawable.gift_generic),
        Gift(2, "Gift 2", 19.99, R.drawable.gift_generic),
        Gift(3, "Gift 3", 34.99, R.drawable.gift_generic),
        Gift(4, "Gift 4", 14.99, R.drawable.gift_generic),
        Gift(5, "Gift 5", 29.99, R.drawable.gift_generic),
        Gift(6, "Gift 6", 39.99, R.drawable.gift_generic),
        Gift(7, "Gift 7", 49.99, R.drawable.gift_generic),
        Gift(8, "Gift 8", 59.99, R.drawable.gift_generic),
    )

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2), // 2 columns in the grid
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between columns
    ) {
        items(gifts) { gift ->
            GiftCard(gift = gift, onGiftClick = onGiftClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(onGiftClick = {})
}