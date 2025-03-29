package com.example.giftshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giftshop.R
import com.example.giftshop.data.Gift

/* Page for a single Product  */
@Composable
fun ProductPage(
    modifier: Modifier = Modifier,
    gift: Gift = Gift(1, "Gift 1", 24.99, R.drawable.gift_generic)
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = gift.imageId),
            contentDescription = gift.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = gift.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "$${gift.price}",
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add to Cart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPagePreview() {
    ProductPage()
}