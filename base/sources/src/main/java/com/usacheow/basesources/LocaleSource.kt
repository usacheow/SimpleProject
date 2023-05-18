package com.usacheow.basesources

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.usacheow.corecommon.strings.DEFAULT_LOCALE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn

class LocaleSource(
    scope: CoroutineScope,
    private val context: Context,
) {

    val stateFlow = callbackFlow {
        val receiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {
                if (intent.action == Intent.ACTION_LOCALE_CHANGED) {
                    val locale = context?.resources?.configuration?.locales?.get(0)
                    if (locale != null) trySend(locale)
                }
            }
        }
        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_LOCALE_CHANGED))

        awaitClose { context.unregisterReceiver(receiver) }
    }.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = DEFAULT_LOCALE,
    )
}