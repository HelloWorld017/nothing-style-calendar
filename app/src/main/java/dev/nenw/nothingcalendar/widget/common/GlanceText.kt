package dev.nenw.nothingcalendar.widget.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.TypedValueCompat.dpToPx
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import dev.nenw.nothingcalendar.widget.common.utils.LocalMultiplier
import dev.nenw.nothingcalendar.widget.common.theme.GlanceTextStyle
import dev.nenw.nothingcalendar.widget.common.theme.TypefaceDefinition

fun textAsBitmap(
    ctx: Context,
    text: String,
    fontSize: Dp,
    color: Color = Color.Black,
    letterSpacing: Float = 0.1f,
    font: Typeface?
): Bitmap {
    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    paint.textSize = dpToPx(fontSize.value, (ctx.resources.displayMetrics))
    paint.color = color.toArgb()
    paint.letterSpacing = letterSpacing
    paint.typeface = font

    val baseline = -paint.ascent()
    val width = (paint.measureText(text)).toInt()
    val height = (baseline + paint.descent()).toInt()
    val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    canvas.drawText(text, 0f, baseline, paint)
    return image
}

@Composable
fun GlanceText(
    text: String,
    textStyle: GlanceTextStyle,
    modifier: GlanceModifier = GlanceModifier,
    color: Color = Color.Black,
) {
    Image(
        modifier = modifier,
        provider = ImageProvider(
            textAsBitmap(
                ctx = LocalContext.current,
                text = text,
                font = when (textStyle.font) {
                    is TypefaceDefinition.ByTypeface -> textStyle.font.typeface
                    is TypefaceDefinition.ByResourceId -> ResourcesCompat.getFont(
                        LocalContext.current,
                        textStyle.font.font
                    )
                },
                fontSize = textStyle.fontSize * LocalMultiplier.current,
                letterSpacing = textStyle.letterSpacing.value * LocalMultiplier.current,
                color = color,
            )
        ),
        contentDescription = null,
    )
}