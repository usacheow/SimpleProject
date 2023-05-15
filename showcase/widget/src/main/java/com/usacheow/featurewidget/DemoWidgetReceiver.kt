package com.usacheow.featurewidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DemoWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = DemoWidget()

    private val coroutineScope = MainScope()

    companion object {

        val counterKey = longPreferencesKey("counterKey")

        private const val IncrementedCounterKey = "IncrementedCounterKey"

        fun intent(
            context: Context,
            incrementedCounter: Long?,
        ) = Intent(context, DemoWidgetReceiver::class.java).apply {
            putExtra(IncrementedCounterKey, incrementedCounter)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.hasExtra(IncrementedCounterKey)) {
            coroutineScope.launch {
                saveNewCounterValue(context, intent.getLongExtra(IncrementedCounterKey, 0))
            }
        }
    }

    private fun saveNewCounterValue(context: Context, value: Long) = coroutineScope.launch {
        val glanceIds = GlanceAppWidgetManager(context).getGlanceIds(DemoWidget::class.java)

        glanceIds.forEach { glanceId ->
            updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { pref ->
                pref.toMutablePreferences().apply { this[counterKey] = value }
            }
            glanceAppWidget.update(context, glanceId)
        }
    }
}