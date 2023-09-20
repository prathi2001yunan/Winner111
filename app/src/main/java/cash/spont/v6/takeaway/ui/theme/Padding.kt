package cash.spont.v6.takeaway.ui.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val verySmall: Dp = 2.dp,
    val extraSmall: Dp = 4.dp
)

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = compositionLocalOf { Padding() }.current

@Composable
fun MaterialTheme.statusBarPadding(): Dp =
    WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

@Composable
fun MaterialTheme.navigationBarPadding(): Dp =
    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
