package dev.nenw.nothingcalendar.widget.weekcalendar

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.preview.Surfaces
import dev.nenw.nothingcalendar.widget.common.GlanceText
import dev.nenw.nothingcalendar.widget.common.modifiers.widgetCornerRadius
import dev.nenw.nothingcalendar.widget.common.theme.GlanceThemeKind
import dev.nenw.nothingcalendar.widget.common.theme.GlanceThemeProvider
import dev.nenw.nothingcalendar.widget.common.theme.GlanceTypography
import dev.nenw.nothingcalendar.widget.common.theme.LocalGlanceTheme
import dev.nenw.nothingcalendar.widget.common.utils.MultiplierProvider
import dev.nenw.nothingcalendar.widget.common.utils.mulDp
import dev.nenw.nothingcalendar.widget.weekcalendar.utils.WeekCalendarDay
import dev.nenw.nothingcalendar.widget.weekcalendar.utils.WeekCalendarDayKind
import dev.nenw.nothingcalendar.widget.weekcalendar.utils.getWeekCalendar
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun WeekCalendarItem(day: WeekCalendarDay, modifier: GlanceModifier = GlanceModifier) {
    Column (
        modifier = GlanceModifier
            .padding(3.mulDp)
            .background(when (day.kind) {
                WeekCalendarDayKind.TODAY -> LocalGlanceTheme.current.bgHighlight
                WeekCalendarDayKind.DEFAULT -> LocalGlanceTheme.current.bgBase
            })
            .cornerRadius(5.mulDp)
            .width(20.mulDp)
            .then(modifier)
    ) {
        val color = when (day.kind) {
            WeekCalendarDayKind.TODAY -> LocalGlanceTheme.current.fillHighlightPrimary
            WeekCalendarDayKind.DEFAULT -> LocalGlanceTheme.current.fillBasePrimary
        }

        GlanceText(
            text = day.weekDay,
            textStyle = GlanceTypography.finePrint,
            color = color,
        )
        GlanceText(
            text = "${day.value}",
            textStyle = GlanceTypography.number1,
            color = color,
        )
    }
}

@Composable
fun WeekCalendarContent(date: ZonedDateTime) {
    val weekCalendar = remember { getWeekCalendar(date) }
    Row (modifier = GlanceModifier.fillMaxWidth()) {
        weekCalendar.calendar.forEach { day ->
            WeekCalendarItem(day, modifier = GlanceModifier.defaultWeight())
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Composable
@Preview(Surfaces.APP_WIDGET)
fun WeekCalendar(date: ZonedDateTime = ZonedDateTime.now()) {
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase()

    GlanceThemeProvider(GlanceThemeKind.DARK) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(LocalGlanceTheme.current.bgBase)
                .padding(9.mulDp)
                .widgetCornerRadius()
        ) {
            GlanceText(
                text = "$month ${date.dayOfMonth}",
                textStyle = GlanceTypography.heading1,
                color = LocalGlanceTheme.current.fillBasePrimary,
                modifier = GlanceModifier.padding(start = 3.mulDp)
            )
            Spacer(modifier = GlanceModifier.defaultWeight())
            WeekCalendarContent(date)
        }
    }
}


class WeekCalendarWidget(private val date: ZonedDateTime): GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Exact
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MultiplierProvider(defaultRatio = 2.0f, baseSize = 180.dp) {
                WeekCalendar(date)
            }
        }
    }
}

class WeekCalendarWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget = WeekCalendarWidget(ZonedDateTime.now())
}