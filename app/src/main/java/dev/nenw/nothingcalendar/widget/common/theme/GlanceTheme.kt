package dev.nenw.nothingcalendar.widget.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

enum class GlanceThemeKind {
    DARK, LIGHT
}

data class GlanceTheme (
    val bgBase: Color,
    val fillBasePrimary: Color,
    val fillBaseSecondary: Color,

    val bgHighlight: Color,
    val fillHighlightPrimary: Color,
)

private val GlanceLightTheme = GlanceTheme (
    bgBase = Color(0xfff1f0f6),
    fillBasePrimary = Color(0xff1c1b21),
    fillBaseSecondary = Color(0xffafa7a5),

    bgHighlight = Color(0xffd71921),
    fillHighlightPrimary = Color(0xfff1f0f6),
)

private val GlanceDarkTheme = GlanceTheme (
    bgBase = Color(0xff1c1b21),
    fillBasePrimary = Color(0xfff1f0f6),
    fillBaseSecondary = Color(0xff414141),

    bgHighlight = Color(0xffd71921),
    fillHighlightPrimary = Color(0xfff1f0f6),
)

val LocalGlanceTheme = compositionLocalOf {
    GlanceDarkTheme
}

@Composable
fun GlanceThemeProvider(kind: GlanceThemeKind, content: @Composable () -> Unit) {
    val theme = when (kind) {
        GlanceThemeKind.LIGHT -> GlanceLightTheme
        GlanceThemeKind.DARK -> GlanceDarkTheme
    }

    CompositionLocalProvider(LocalGlanceTheme provides theme) {
        content()
    }
}