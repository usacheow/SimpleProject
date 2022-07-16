package com.usacheow.corenavigation.base

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Browser
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.core.os.bundleOf

fun Context.openShareDialog(data: String) = ShareCompat.IntentBuilder(this)
    .setText(data)
    .setType("text/plain")
    .startChooser()

fun Context.openUrl(url: String) = CustomTabsIntent.Builder()
    .setShowTitle(true)
    .setDefaultColorSchemeParams(CustomTabColorSchemeParams.Builder().build())
    .build()
    .apply { intent.putExtra(Browser.EXTRA_HEADERS, bundleOf()) }
    .launchUrl(this, Uri.parse(url))

fun Context.openMaps(address: String) = openIntent(
    Uri.parse("http://maps.google.com/maps?q=$address"),
)

fun Context.openMaps(latitude: Double, longitude: Double) = openIntent(
    Uri.parse("geo:<$latitude>,<$longitude>?q=<$latitude>,<$longitude>"),
)

fun Context.openDialer(phoneNumber: String) = openIntent(
    Uri.parse("tel:$phoneNumber"),
    Intent.ACTION_DIAL,
)

fun Context.openStore(packageName: String) = openIntent(Uri.parse("market://details?id=$packageName")) {
    openIntent(Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
}

fun Context.openMail(mail: String, subject: String) = openIntent(
    Uri.parse("mailto:$mail?subject=$subject"),
)

fun Context.openImage(uri: Uri) = startActivity(
    Intent(Intent.ACTION_VIEW)
        .setDataAndType(uri, "image/*")
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION),
)

private fun Context.openIntent(
    uri: Uri,
    action: String = Intent.ACTION_VIEW,
    onError: (() -> Boolean)? = null,
) = try {
    startActivity(Intent(action, uri))
    true
} catch (e: ActivityNotFoundException) {
    onError?.invoke() ?: false
}