package presentation.screen.common

import androidx.compose.ui.graphics.Color

sealed interface Theme {
    object Brown : Theme
    object Classic : Theme
    object Gray : Theme
}

fun mapPositionToColor(theme: Theme, position: Pair<Int, Int>, reversed: Boolean = false): Color {
    val lightColor = getLightColor(theme)
    val darkColor = getDarkColor(theme)

    return if ((position.first + position.second) % 2 == 0) {
        if (reversed) lightColor else darkColor
    } else {
        if (reversed) darkColor else lightColor
    }
}

private fun getLightColor(theme: Theme): Color {
    return when (theme) {
        is Theme.Brown -> lightBrown
        is Theme.Classic -> white
        is Theme.Gray -> lightGray
    }
}

private fun getDarkColor(theme: Theme): Color {
    return when (theme) {
        is Theme.Brown -> darkBrown
        is Theme.Classic -> black
        is Theme.Gray -> darkGray
    }
}