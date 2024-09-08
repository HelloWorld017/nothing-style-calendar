package dev.nenw.nothingcalendar.widget.fullcalendar

import android.content.Context
import androidx.glance.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.min
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.preview.Surfaces
import dev.nenw.nothingcalendar.widget.common.GlanceText
import dev.nenw.nothingcalendar.widget.common.utils.MultiplierProvider
import dev.nenw.nothingcalendar.widget.common.modifiers.widgetCornerRadius
import dev.nenw.nothingcalendar.widget.common.theme.GlanceThemeKind
import dev.nenw.nothingcalendar.widget.common.theme.GlanceThemeProvider
import dev.nenw.nothingcalendar.widget.common.theme.GlanceTypography
import dev.nenw.nothingcalendar.widget.common.theme.LocalGlanceTheme
import dev.nenw.nothingcalendar.widget.common.utils.mulDp
import dev.nenw.nothingcalendar.widget.fullcalendar.utils.FullCalendarDay
import dev.nenw.nothingcalendar.widget.fullcalendar.utils.FullCalendarDayKind
import dev.nenw.nothingcalendar.widget.fullcalendar.utils.getFullCalendar
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun FullCalendarItem(
    day: FullCalendarDay,
    modifier: GlanceModifier = GlanceModifier,
) {
    GlanceText(
        text = day.value?.toString() ?: " ",
        textStyle = GlanceTypography.number1,
        modifier = GlanceModifier
            .width(18.mulDp)
            .height(18.mulDp)
            .padding(2.mulDp)
            .then(modifier)
            .then(
                when (day.kind) {
                    FullCalendarDayKind.TODAY ->
                        GlanceModifier
                            .background(LocalGlanceTheme.current.bgHighlight)
                            .cornerRadius(5.mulDp)
                    else -> GlanceModifier
                }
            ),
        color = when (day.kind) {
            FullCalendarDayKind.TODAY -> LocalGlanceTheme.current.fillHighlightPrimary
            FullCalendarDayKind.ACTIVE -> LocalGlanceTheme.current.fillBasePrimary
            FullCalendarDayKind.INACTIVE -> LocalGlanceTheme.current.fillBaseSecondary
        }
    )
}

@Composable
fun FullCalendarWeek(week: List<FullCalendarDay>,) {
    Row {
        week.forEach { day -> FullCalendarItem(day) }
    }
}

@Composable
fun FullCalendarContents(date: ZonedDateTime) {
    val fullCalendar = remember { getFullCalendar(date) }

    Column {
        for (week in fullCalendar.calendar) {
            FullCalendarWeek(week)
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Composable
@Preview(Surfaces.APP_WIDGET)
fun FullCalendar(date: ZonedDateTime = ZonedDateTime.now()) {
    GlanceThemeProvider(GlanceThemeKind.DARK) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(LocalGlanceTheme.current.bgBase)
                .padding(12.mulDp)
                .widgetCornerRadius(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = GlanceModifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start
            ){
                GlanceText(
                    text = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase(),
                    textStyle = GlanceTypography.heading1,
                    color = LocalGlanceTheme.current.fillBasePrimary,
                )
                GlanceText(
                    text = date.year.toString(),
                    textStyle = GlanceTypography.number2,
                    color = LocalGlanceTheme.current.fillBasePrimary,
                )
                Spacer(modifier = GlanceModifier.defaultWeight())
                FullCalendarContents(date)
            }
        }
    }
}

class FullCalendarWidget(private val date: ZonedDateTime): GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Exact
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MultiplierProvider(defaultRatio = 1.0f) {
                FullCalendar(date)
            }
        }
    }
}

class FullCalendarWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget = FullCalendarWidget(ZonedDateTime.now())
}