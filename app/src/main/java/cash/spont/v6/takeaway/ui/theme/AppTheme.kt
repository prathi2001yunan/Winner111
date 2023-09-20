package cash.spont.v6.takeaway.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color.Black,
)
private val DarkColors = darkColorScheme(
    primary = Color.White,
    background = Color.Black,
    onBackground = Color.Black
)

private val AppTypography = Typography()

/**
 * App-wide dynamic theme
 * @param content [Composable] UI
 */
@Composable
fun appTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
): Color {
    MaterialTheme(
        colorScheme = if (useDarkTheme) {
            DarkColors
        } else {
            LightColors
        },
        typography = AppTypography,
        content = content
    )
    var color = Color(0xFFF6F6FC)
    if (useDarkTheme) {
        color = Color(0xFF19191E)
    }
    return color
}
