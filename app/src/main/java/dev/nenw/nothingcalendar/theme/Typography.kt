package dev.nenw.nothingcalendar.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import dev.nenw.nothingcalendar.R

val matrixSansPrint = FontFamily(Font(R.font.matrix_sans_print));
val nothingFont = FontFamily(Font(R.font.nothing_font));

object Typography {
    val heading1 = TextStyle(
        fontSize = 30.sp,
        fontFamily = nothingFont,
        lineHeight = 48.sp,
    );

    val heading2 = TextStyle(
        fontSize = 20.sp,
        fontFamily = nothingFont,
        lineHeight = 28.sp,
    );
}