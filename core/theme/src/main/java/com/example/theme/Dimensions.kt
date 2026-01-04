package com.example.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val paddingXXSmall: Dp = 4.dp,
    val paddingXSmall: Dp = 6.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingXMedium: Dp = 16.dp,
    val paddingMedium: Dp = 20.dp,
    val paddingLarge: Dp = 24.dp,
    val paddingXLarge: Dp = 28.dp,
    val paddingXXLarge: Dp = 32.dp,

    val cornerRadiusSmall: Dp = 12.dp,
    val cornerRadiusMedium: Dp = 16.dp,
    val cornerRadiusLarge: Dp = 28.dp,
    val cornerRadiusXLarge: Dp = 30.dp,
    val cornerRadiusFull: Dp = 100.dp,

    val iconSizeSmall: Dp = 14.dp,
    val iconSizeMedium: Dp = 17.dp,
    val iconSizeLarge: Dp = 24.dp,
    val iconSizeXLarge: Dp = 32.dp,

    val buttonHeightSmall: Dp = 40.dp,
    val buttonHeightMedium: Dp = 44.dp,
    val buttonHeightLarge: Dp = 52.dp,
    val buttonSpecialHeight: Dp = 156.dp,

    val avatarSizeSmall: Dp = 24.dp,
    val avatarSizeMedium: Dp = 40.dp,
    val avatarSizeLarge: Dp = 56.dp,

    val cardImageHeight: Dp = 132.dp,
    val detailImageHeight: Dp = 240.dp,

    val ratingPanelWidth: Dp = 50.dp,
    val datePanelHeight: Dp = 24.dp,

    val dividerHeight: Dp = 2.dp,

    val rowHeightMedium: Dp = 100.dp,
    val rowHeightLarge: Dp = 210.dp,
    val columnHeight: Dp = 140.dp
)

val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }

val ITCoursesApplicationTheme.dimensions: AppDimensions
    @Composable
    get() = LocalAppDimensions.current