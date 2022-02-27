package com.usacheow.corenavigation.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Browser
import android.provider.Settings
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Fragment.openDialer(phoneNumber: String): Boolean {
    val uri = Uri.parse("tel:$phoneNumber")
    return openIntent(uri, Intent.ACTION_DIAL)
}

fun Fragment.openStore(packageName: String = requireActivity().packageName) {
    val uri = Uri.parse("market://details?id=$packageName")
    openIntent(uri) {
        val url = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        openIntent(url)
    }
}

fun Fragment.openMail(mail: String, subject: String): Boolean {
    val uri = Uri.parse("mailto:$mail?subject=$subject")
    return openIntent(uri)
}

fun Fragment.openMaps(address: String): Boolean {
    val uri = Uri.parse("http://maps.google.com/maps?q=$address")
    return openIntent(uri)
}

fun Fragment.openMaps(latitude: Double, longitude: Double): Boolean {
    val uri = Uri.parse("geo:<$latitude>,<$longitude>?q=<$latitude>,<$longitude>(Вам сюда)")
    return openIntent(uri)
}

fun Fragment.openUrl(url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setDefaultColorSchemeParams(CustomTabColorSchemeParams.Builder().build())
        .build()
        .apply { intent.putExtra(Browser.EXTRA_HEADERS, bundleOf()) }
        .launchUrl(requireContext(), Uri.parse(url))
}

fun Fragment.openShareDialog(url: String) {
    ShareCompat.IntentBuilder(requireActivity())
        .setText(url)
        .setType("text/plain")
        .startChooser()
}

fun Fragment.openKeyboardSettingsScreen() {
    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
    startActivity(intent)
}

private fun Fragment.openIntent(
    uri: Uri,
    action: String = Intent.ACTION_VIEW,
    onError: (() -> Boolean)? = null,
): Boolean {
    return try {
        val intent = Intent(action, uri)
        context?.startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        onError?.invoke() ?: false
    }
}