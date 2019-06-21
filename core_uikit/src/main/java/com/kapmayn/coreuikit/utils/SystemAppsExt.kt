package com.kapmayn.coreuikit.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kapmayn.coreuikit.R

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

fun Fragment.openStoreApp(packageName: String) {
    val storeUri = "market://details?id=$packageName"
    val storeUrl = "https://play.google.com/store/apps/details?id=$packageName"
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(storeUri))
        context?.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(storeUrl))
        context?.startActivity(intent)
    }
}

fun Fragment.openEmailApp(mail: String, subject: String) {
    val feedbackTheme = "mailto:$mail?subject=$subject"
    val uri = Uri.parse(feedbackTheme)
    val intent = Intent(Intent.ACTION_VIEW).setData(uri)
    context?.startActivity(intent)
}

fun Fragment.openCustomTab(url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        .build()
        .launchUrl(context, Uri.parse(url))
}