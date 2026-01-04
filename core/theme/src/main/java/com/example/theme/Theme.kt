package com.example.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ITCoursesApplication(
    val background: Color = DarkBackground,
    val cardBackground: Color = CardBackground,
    val inputBackground: Color = InputBackground,
    val divider: Color = Divider,
    val textPrimary: Color = TextPrimary,
    val textSecondary: Color = TextPrimary.copy(alpha = 0.5f),
    val textTertiary: Color = TextPrimary.copy(alpha = 0.8f),
    val textMuted: Color = TextPrimary.copy(alpha = 0.4f),
    val accent: Color = AccentGreen,
    val error: Color = ErrorRed,
    val overlay: Color = OverlayBackground.copy(alpha = 0.7f),
    val vkBlue: Color = VkBlue,
    val okOrangeStart: Color = OkOrangeStart,
    val okOrangeEnd: Color = OkOrangeEnd,
    val iconTint: Color = TextPrimary,
    val buttonDisabled: Color = AccentGreen.copy(alpha = 0.5f),
    val cursorColor: Color = TextPrimary.copy(alpha = 0.5f)
)

val LocalITCoursesApplication = staticCompositionLocalOf { ITCoursesApplication() }

private val DarkColorScheme = darkColorScheme(
    primary = AccentGreen,
    secondary = CardBackground,
    tertiary = InputBackground,
    background = DarkBackground,
    surface = CardBackground,
    onPrimary = TextPrimary,
    onSecondary = TextPrimary,
    onTertiary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = ErrorRed,
    onError = TextPrimary
)

@Composable
fun ITCoursesApplicationTheme(
    content: @Composable () -> Unit
) {
    val itCoursesApplication = ITCoursesApplication()

    CompositionLocalProvider(LocalITCoursesApplication provides itCoursesApplication) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = AppTypography,
            content = content
        )
    }
}

object ITCoursesApplicationTheme {
    val colors: ITCoursesApplication
        @Composable
        get() = LocalITCoursesApplication.current
}