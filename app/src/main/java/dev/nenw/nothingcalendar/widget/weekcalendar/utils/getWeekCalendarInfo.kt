package dev.nenw.nothingcalendar.widget.weekcalendar.utils

import java.time.YearMonth
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

enum class WeekCalendarDayKind {
    TODAY, DEFAULT
}

data class WeekCalendarDay(
    val kind: WeekCalendarDayKind,
    val weekDay: String,
    val value: Int
)

data class WeekCalendarInfo(
    val calendar: List<WeekCalendarDay>,
);

fun getWeekCalendar(today: ZonedDateTime): WeekCalendarInfo {
    val dayOfWeekField = WeekFields.of(Locale.getDefault()).dayOfWeek()
    val calendar = (1..7).map { dayOfWeek ->
        val date = today.with(dayOfWeekField, dayOfWeek.toLong())
        WeekCalendarDay(
            kind =
                if (date.dayOfMonth == today.dayOfMonth) WeekCalendarDayKind.TODAY
                else WeekCalendarDayKind.DEFAULT,
            weekDay = date.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ENGLISH),
            value = date.dayOfMonth
        )
    }

    return WeekCalendarInfo(calendar)
}