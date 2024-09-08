package dev.nenw.nothingcalendar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.nenw.nothingcalendar.theme.AppTheme
import dev.nenw.nothingcalendar.theme.Typography
import dev.nenw.nothingcalendar.theme.nothingFont
import dev.nenw.nothingcalendar.widget.FullCalendarWidgetPreview
import dev.nenw.nothingcalendar.widget.WeekCalendarWidgetPreview
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun Header(state: CollapsingToolbarScaffoldState, modifier: Modifier) {
    val progress = state.toolbarState.progress

    Row(
        modifier = modifier
            .height(120.dp)
            .padding(32.dp, 20.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "WIDGETS GALLERY",
            fontSize = (30 * progress + 18 * (1 - progress)).sp,
            fontFamily = nothingFont,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun MainActivityContents() {
    val toolbarState = rememberCollapsingToolbarScaffoldState()

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            CollapsingToolbarScaffold(
                state = toolbarState,
                toolbar = {
                    Header(toolbarState, modifier = Modifier.parallax(1f))
                    Column(modifier = Modifier.height(60.dp)) {}
                },
                enabled = true,
                modifier = Modifier.fillMaxSize(),
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(100.dp))

                    FullCalendarWidgetPreview()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "FULL CALENDAR",
                        fontFamily = nothingFont,
                        style = Typography.heading2,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(100.dp))

                    WeekCalendarWidgetPreview()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "WEEK CALENDAR",
                        fontFamily = nothingFont,
                        style = Typography.heading2,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalContext provides this.applicationContext) {
                MainActivityContents()
            }
        }
    }
}