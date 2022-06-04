package presentation.screen.board.component

import androidx.compose.runtime.Composable
import domain.model.Board
import domain.model.Cell

@Composable
fun BoardBlackView(board: Board) {
    val rows = splitCellsInRows(board)
    BoardView(rows)
}

private fun splitCellsInRows(board: Board): List<List<Cell>> {
    val rows = arrayListOf<List<Cell>>()
    for (row in 0 until 8) {
        val sublist = board.cells.subList(8 * row, 8 * (row + 1)).reversed()
        rows.add(sublist)
    }
    return rows
}