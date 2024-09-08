package dev.nenw.nothingcalendar.widget.common.theme

import android.graphics.Typeface
import androidx.annotation.FontRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.nenw.nothingcalendar.R

sealed class TypefaceDefinition {
    data class ByTypeface(val typeface: Typeface): TypefaceDefinition()
    data class ByResourceId(@FontRes val font: Int): TypefaceDefinition()
}

data class GlanceTextStyle(
    val font: TypefaceDefinition,
    val fontSize: Dp,
    val lineHeight: Dp,
    val letterSpacing: Dp = (-0.01).dp,
)

object GlanceTypography {
    val finePrint = GlanceTextStyle(
        font = TypefaceDefinition.ByTypeface(Typeface.DEFAULT),
        fontSize = 6.dp,
        lineHeight = 8.dp,
    )

    val number1 = GlanceTextStyle(
        font = TypefaceDefinition.ByResourceId(R.font.matrix_sans_print),
        fontSize = 14.dp,
        lineHeight = 17.dp,
    )

    val number2 = GlanceTextStyle(
        font = TypefaceDefinition.ByResourceId(R.font.matrix_sans_print),
        fontSize = 11.dp,
        lineHeight = 17.dp,
    )

    val heading1 = GlanceTextStyle(
        font = TypefaceDefinition.ByResourceId(R.font.nothing_font),
        fontSize = 15.dp,
        lineHeight = 24.dp,
    )

    val heading2 = GlanceTextStyle(
        font = TypefaceDefinition.ByResourceId(R.font.nothing_font),
        fontSize = 13.dp,
        lineHeight = 20.dp,
    )
}