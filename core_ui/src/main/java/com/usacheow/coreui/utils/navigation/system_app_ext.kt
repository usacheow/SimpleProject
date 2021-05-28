package com.usacheow.coreui.utils.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Browser
import android.provider.Settings
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.usacheow.coreui.R
import com.usacheow.coreui.utils.view.color

fun Fragment.openDialer(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}

fun Fragment.openStore(packageName: String) {
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

fun Fragment.openMail(mail: String, subject: String) {
    val feedbackTheme = "mailto:$mail?subject=$subject"
    val uri = Uri.parse(feedbackTheme)
    val intent = Intent(Intent.ACTION_VIEW).setData(uri)
    context?.startActivity(intent)
}

fun Fragment.openUrl(url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setToolbarColor(color(R.color.backgroundToolbar))
        .build()
        .apply {
            intent.putExtra(Browser.EXTRA_HEADERS, bundleOf())
        }
        .launchUrl(context, Uri.parse(url))
}

fun Fragment.openMaps(address: String) {
    val geoUri = "http://maps.google.com/maps?q=$address"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
    startActivity(intent)
}

fun Fragment.openMaps(latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:<$latitude>,<$longitude>?q=<$latitude>,<$longitude>(Вам сюда)")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun Fragment.openShareDialog(url: String) {
    activity?.let {
        ShareCompat.IntentBuilder(it)
            .setText(url)
            .setType("text/plain")
            .startChooser()
    }
}

fun Fragment.openKeyboardSettingsScreen() {
    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
    startActivity(intent)
}