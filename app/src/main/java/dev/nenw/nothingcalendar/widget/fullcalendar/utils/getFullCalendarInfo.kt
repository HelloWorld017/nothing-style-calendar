package dev.nenw.nothingcalendar.widget.fullcalendar.utils

import java.time.YearMonth
import java.time.ZonedDateTime
import java.time.temporal.WeekFields
import java.util.Locale

enum class FullCalendarDayKind {
    TODAY, ACTIVE, INACTIVE
}

data class FullCalendarDay(
    val kind: FullCalendarDayKind,
    val value: Int?
)

data class FullCalendarInfo(
    val calendar: List<List<FullCalendarDay>>,
);

fun getFullCalendar(date: ZonedDateTime): FullCalendarInfo {
    val weekOfMonthField = WeekFields.of(Locale.getDefault()).weekOfMonth()
    val dayOfWeekField = WeekFields.of(Locale.getDefault()).dayOfWeek()

    val yearMonth = YearMonth.from(date)
    val dayOfWeekOfStart = yearMonth.atDay(1).get(dayOfWeekField)
    val dayOfMonthOfEnd = yearMonth.atEndOfMonth().dayOfMonth
    val dayOfMonthOfCurrent = date.dayOfMonth
    val weekOfMonthOfCurrent = date.get(weekOfMonthField)

    val padding = (2..dayOfWeekOfStart).map { _ -> null }
    val dates = (1..dayOfMonthOfEnd).map { value -> value }
    val calendar = (padding + dates)
        .map { value -> FullCalendarDay(
            kind = when (value) {
                null -> FullCalendarDayKind.INACTIVE
                dayOfMonthOfCurrent -> FullCalendarDayKind.TODAY
                else -> when (yearMonth.atDay(value).get(weekOfMonthField)) {
                    weekOfMonthOfCurrent -> FullCalendarDayKind.ACTIVE
                    else -> FullCalendarDayKind.INACTIVE
                }
            },
            value = value
        ) }
        .chunked(7)

    return FullCalendarInfo(calendar)
}