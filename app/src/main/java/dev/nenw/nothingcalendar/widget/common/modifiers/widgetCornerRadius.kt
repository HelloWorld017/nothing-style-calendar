package dev.nenw.nothingcalendar.widget.common.modifiers

import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius

fun GlanceModifier.widgetCornerRadius(): GlanceModifier {
    return this.cornerRadius(android.R.dimen.system_app_widget_inner_radius)
}
