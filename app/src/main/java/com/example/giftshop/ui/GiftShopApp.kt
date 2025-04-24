package com.example.giftshop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun GiftShopApp() {
    val navController = rememberNavController()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(onTabSelected = { newIndex ->
                selectedTabIndex = newIndex
                if (newIndex == 0) {
                    navController.navigate("homePage") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, selectedTabIndex = selectedTabIndex)

        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (selectedTabIndex) {
                0 -> NavHost(
                    navController = navController,
                    startDestination = "homePage"
                ) {
                    composable("homePage") { HomePage(navController) }
                    composable(
                        "product/{giftId}",
                        arguments = listOf(navArgument("giftId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val giftId = backStackEntry.arguments?.getInt("giftId") ?: 0
                        ProductPage(navController = navController, giftId = giftId)
                    }
                }

                1 -> CartPage()
                2 -> OrdersPage()
                3 -> ProfilePage()
            }
        }
    }
}