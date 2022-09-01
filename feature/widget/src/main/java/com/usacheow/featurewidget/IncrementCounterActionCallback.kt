package com.usacheow.featurewidget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

class IncrementCounterActionCallback : ActionCallback {

    companion object {
        val IncrementedCounterKey = ActionParameters.Key<Long>("IncrementedCounterKey")
    }

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val intent = DemoWidgetReceiver.intent(context, parameters[IncrementedCounterKey])
        context.sendBroadcast(intent)
    }
}