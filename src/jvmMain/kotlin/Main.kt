// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import di.initKoin
import presentation.screen.board.BoardScreen
import presentation.screen.common.*

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = windowTopBarTitle,
        state = WindowState(
            size = DpSize((cellSize * boardRows + 400).dp, (cellSize * boardColumns + windowTopBarHeight).dp)
        )
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        BoardScreen()
    }
}
