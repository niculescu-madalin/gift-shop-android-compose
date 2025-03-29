package com.example.giftshop

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giftshop.ui.theme.GiftShopTheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier
    ) {
        NavigationItems.navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title
                    )
                },
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    onTabSelected(index)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    GiftShopTheme {
        BottomNavigationBar(
            Modifier.padding(top = 24.dp),
            onTabSelected = {}
        )
    }
}
