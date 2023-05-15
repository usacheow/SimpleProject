package com.usacheow.featurewidget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.Action
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ColumnScope
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.usacheow.featurewidget.glance.GlanceTheme
import com.usacheow.featurewidget.glance.appWidgetBackgroundModifier
import com.usacheow.featurewidget.glance.cornerRadius
import com.usacheow.featurewidget.glance.stringResource

class DemoWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
    }

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val counter = prefs[DemoWidgetReceiver.counterKey]

        GlanceTheme {
            Column(
                modifier = appWidgetBackgroundModifier()
                    .padding(GlanceTheme.values.largeContentPadding.dp),
            ) {
                Counter(counter)
                Spacer(modifier = GlanceModifier.height(16.dp))
                OpenApp()
            }
        }
    }

    @Composable
    private fun ColumnScope.Counter(value: Long?) {
        val params = actionParametersOf(
            IncrementCounterActionCallback.IncrementedCounterKey to (value?.plus(1) ?: 1L),
        )

        ButtonContainer(actionRunCallback<IncrementCounterActionCallback>(params)) {
            Text(
                text = value?.toString() ?: stringResource(R.string.widget_button_start),
                style = TextStyle(
                    fontSize = value?.let { 32.sp } ?: 20.sp,
                    color = GlanceTheme.colors.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                ),
                modifier = GlanceModifier.padding(16.dp),
            )
        }
    }

    @Composable
    private fun ColumnScope.OpenApp() {
        val intent = Intent(Intent.ACTION_VIEW, "app://com.usacheow.appdemo/demo".toUri())
        ButtonContainer(actionStartActivity(intent)) {
            Text(
                text = stringResource(R.string.widget_button_open_app),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = GlanceTheme.colors.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                ),
                modifier = GlanceModifier.padding(16.dp),
            )
        }
    }

    @Composable
    private fun ColumnScope.ButtonContainer(
        action: Action,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .defaultWeight()
                .cornerRadius(GlanceTheme.values.innerLargeRadius)
                .background(GlanceTheme.colors.primaryContainer)
                .clickable(action),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}