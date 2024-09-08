package dev.nenw.nothingcalendar.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.compose
import com.google.android.glance.appwidget.host.AppWidgetHost
import com.google.android.glance.appwidget.host.rememberAppWidgetHostState
import dev.nenw.nothingcalendar.widget.fullcalendar.FullCalendarWidget
import dev.nenw.nothingcalendar.widget.fullcalendar.FullCalendarWidgetReceiver
import dev.nenw.nothingcalendar.widget.weekcalendar.WeekCalendarWidget
import dev.nenw.nothingcalendar.widget.weekcalendar.WeekCalendarWidgetReceiver
import java.time.ZonedDateTime

@Composable
fun WidgetPreview(
    instance: GlanceAppWidget,
    widgetName: ComponentName,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    val hostState = rememberAppWidgetHostState(null)
    val context = LocalContext.current

    suspend fun updateContent() {
        hostState.updateAppWidget(
            instance.compose(context = context, size = size, state = null)
        )
    }


    if (hostState.isReady) {
        LaunchedEffect(hostState.value) {
            updateContent()
        }
    }

    AppWidgetHost(
        modifier = Modifier.clickable {
            AppWidgetManager
                .getInstance(context)
                .requestPinAppWidget(
                    widgetName,
                    null,
                    null
                )
        }.then(modifier),
        displaySize = size,
        state = hostState
    )
}

@Composable
fun FullCalendarWidgetPreview(modifier: Modifier = Modifier) {
    val instance = remember { FullCalendarWidget(ZonedDateTime.now()) }
    val context = LocalContext.current

    WidgetPreview(
        instance = instance,
        widgetName = ComponentName(context, FullCalendarWidgetReceiver::class.java),
        modifier = modifier,
        size = DpSize(200.dp, 200.dp)
    )
}

@Composable
fun WeekCalendarWidgetPreview(modifier: Modifier = Modifier) {
    val instance = remember { WeekCalendarWidget(ZonedDateTime.now()) }
    val context = LocalContext.current

    WidgetPreview(
        instance = instance,
        widgetName = ComponentName(context, WeekCalendarWidgetReceiver::class.java),
        modifier = modifier,
        size = DpSize(200.dp, 100.dp)
    )
}