package com.example.giftshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.giftshop.ui.BottomNavigationBar
import com.example.giftshop.ui.CartPage
import com.example.giftshop.ui.HomePage
import com.example.giftshop.ui.OrdersPage
import com.example.giftshop.ui.ProfilePage
import com.example.giftshop.ui.theme.GiftShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GiftShopTheme {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(onTabSelected = { newIndex ->
                selectedTabIndex = newIndex
            })
        }
    ) { innerPadding ->
        when (selectedTabIndex) {
            0 -> HomePage(modifier = Modifier.padding(innerPadding))
            1 -> CartPage(modifier = Modifier.padding(innerPadding))
            2 -> OrdersPage(modifier = Modifier.padding(innerPadding))
            3 -> ProfilePage(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GiftShopTheme {
        MainScreen()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GiftShopTheme {
        Greeting("Android")
    }
}