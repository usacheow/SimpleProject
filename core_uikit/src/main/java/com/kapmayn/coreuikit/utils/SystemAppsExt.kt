package com.kapmayn.coreuikit.utils

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openMapsApp(latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:<$latitude>,<$longitude>?q=<$latitude>,<$longitude>(Вам сюда)")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun Fragment.openDialerApp(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}