package dev.nenw.nothingcalendar.widget.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.min
import androidx.glance.LocalSize

val LocalMultiplier = compositionLocalOf { 1.0f }

@Composable
fun MultiplierProvider(
    baseSize: Dp = 150.dp,
    defaultRatio: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val (width, height) =
        if (LocalSize.current.isUnspecified) DpSize(200.dp, 200.dp / defaultRatio)
        else LocalSize.current

    val multiplier = min(width, height * defaultRatio) / baseSize
    return CompositionLocalProvider(LocalMultiplier provides multiplier) {
        content()
    }
}

val Int.mulDp: Dp @Composable get() = this.dp * LocalMultiplier.current