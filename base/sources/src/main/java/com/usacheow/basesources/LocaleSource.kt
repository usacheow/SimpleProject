package com.usacheow.basesources

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.usacheow.corecommon.strings.DEFAULT_LOCALE
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn

@Singleton
class LocaleSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationCoroutineScope private val scope: CoroutineScope,
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