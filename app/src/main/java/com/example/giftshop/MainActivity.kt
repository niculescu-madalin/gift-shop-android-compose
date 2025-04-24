package com.example.giftshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.giftshop.ui.GiftShopApp
import com.example.giftshop.ui.theme.GiftShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper.createNotificationChannel(this)
        setContent {
            GiftShopTheme {
                GiftShopApp()
            }
        }
    }
}