package com.example.giftshop.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.giftshop.MainActivity
import com.example.giftshop.R
import com.example.giftshop.data.Cart
import com.example.giftshop.data.CartItem
import com.example.giftshop.data.Order
import com.example.giftshop.data.OrderRepository
import java.text.DecimalFormat

object SimpleNotificationHelper {

    private const val CHANNEL_ID = "simple_notification_channel"
    private const val NOTIFICATION_ID = 100
    private const val TAG = "SimpleNotificationHelper"

    fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Simple Notification Channel"
            val descriptionText = "Channel for simple notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showSimpleNotification(context: Context, message: String) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e(TAG, "Notification permission not granted")
            // In a real app you could show a user-facing message here.
            return
        }

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your own icon!
            .setContentTitle("Thank you for your purchase!")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                notify(NOTIFICATION_ID, builder.build())
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Failed to send notification: ${e.message}")
            // Handle the exception here, maybe tell the user that notifications aren't enabled.
        }
    }
}

@Composable
fun CartPage(modifier: Modifier = Modifier) {
    val cartItems = Cart.items
    val context = LocalContext.current
    var hasNotificationPermission by remember { mutableStateOf(false) }

    // Request permission
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            hasNotificationPermission = isGranted
        }

    // Check permission and ask if necessary
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                hasNotificationPermission = true
            }
        } else {
            hasNotificationPermission = true
        }
    }
    //ensure notification channel is created
    SimpleNotificationHelper.createNotificationChannel(context)

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        if (cartItems.isEmpty()) {
            Text("Your cart is empty", style = MaterialTheme.typography.headlineMedium)
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { cartItem ->
                    CartItemCard(
                        cartItem = cartItem,
                        onQuantityChange = { newQuantity ->
                            Cart.updateItemQuantity(cartItem.gift, newQuantity)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CartSummary(cartItems = cartItems)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (hasNotificationPermission) {
                    // Simulate checkout. We create an order for now.
                    val newOrder = Order(
                        items = Cart.items.toList(),
                        totalPrice = Cart.items.sumOf { it.gift.price * it.quantity })
                    OrderRepository.addOrder(newOrder)
                    //send order notification passing the correct id
                    SimpleNotificationHelper.showSimpleNotification(
                        context,
                        "Your order id is: ${newOrder.id}"
                    )
                    Cart.clear()
                } else {
                    Log.e("CartPage", "Notification permission not granted, so the notification was not send")

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Checkout")
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cartItem.gift.imageId),
                contentDescription = cartItem.gift.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = cartItem.gift.name, fontWeight = FontWeight.Bold)
                Text(text = "$${cartItem.gift.price}")
            }
            QuantitySelector(
                cartItem = cartItem,
                onQuantityChange = onQuantityChange
            )
        }
    }
}

@Composable
fun QuantitySelector(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (cartItem.quantity >= 1) {
                val newQuantity = cartItem.quantity - 1
                onQuantityChange(newQuantity)
            }
        }) {
            Icon(Icons.Filled.Clear, contentDescription = "Remove")
        }
        Text(text = cartItem.quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
        IconButton(onClick = {
            val newQuantity = cartItem.quantity + 1
            onQuantityChange(newQuantity)
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun CartSummary(cartItems: List<CartItem>) {
    val totalPrice = cartItems.sumOf { it.gift.price * it.quantity }
    val formattedTotal = DecimalFormat("#.##").format(totalPrice)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Total", style = MaterialTheme.typography.headlineSmall)
        Text(text = "$${formattedTotal}", style = MaterialTheme.typography.headlineSmall)
    }
}
