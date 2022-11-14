package com.sergio931.weatherapp.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class AppThemes(
    val backgroundColor: Color,
    val primaryColor: Color,
    val textColor: Color,
    val iconsTint: Color,
    val useDarkNavigationIcons: Boolean,
) {

    class RainyTheme(
        backgroundColor: Color = rain,
        primaryColor: Color = Color.White,
        textColor: Color = PurpleGrey40,
        iconsTint: Color = PurpleGrey40,
        useDarkNavigationIcons: Boolean = true,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class CloudyTheme(
        backgroundColor: Color = clouds,
        primaryColor: Color = PurpleGrey40,
        textColor: Color = PurpleGrey80,
        iconsTint: Color = PurpleGrey80,
        useDarkNavigationIcons: Boolean = false,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class SunnyTheme(
        backgroundColor: Color = sun,
        primaryColor: Color = sun,
        textColor: Color = Color.White,
        iconsTint: Color = Color.White,
        useDarkNavigationIcons: Boolean = true,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class AtmosphereTheme(
        backgroundColor: Color = foggy,
        primaryColor: Color = PurpleGrey80,
        textColor: Color = PurpleGrey40,
        iconsTint: Color = PurpleGrey40,
        useDarkNavigationIcons: Boolean = true,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class DrizzleTheme(
        backgroundColor: Color = drizzle,
        primaryColor: Color = PurpleGrey80,
        textColor: Color = PurpleGrey40,
        iconsTint: Color = PurpleGrey40,
        useDarkNavigationIcons: Boolean = true,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class ThunderstormTheme(
        backgroundColor: Color = thunderstorm,
        primaryColor: Color = PurpleGrey40,
        textColor: Color = Color.White,
        iconsTint: Color = Color.White,
        useDarkNavigationIcons: Boolean = false,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

    class SnowTheme(
        backgroundColor: Color = snow,
        primaryColor: Color = Color.White,
        textColor: Color = PurpleGrey40,
        iconsTint: Color = PurpleGrey40,
        useDarkNavigationIcons: Boolean = true,
    ) : AppThemes(
        backgroundColor = backgroundColor,
        primaryColor = primaryColor,
        textColor = textColor,
        iconsTint = iconsTint,
        useDarkNavigationIcons = useDarkNavigationIcons
    )

}