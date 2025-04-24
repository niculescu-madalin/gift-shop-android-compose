package com.example.giftshop.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.giftshop.data.NavigationItems

@Composable
fun BottomNavigationBar(onTabSelected: (Int) -> Unit, selectedTabIndex: Int) {
    NavigationBar {
        NavigationItems.navigationItems.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selectedTabIndex == index) {
                            navigationItem.selectedIcon
                        } else {
                            navigationItem.unselectedIcon
                        },
                        contentDescription = navigationItem.title
                    )
                },
                label = { Text(navigationItem.title) },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}