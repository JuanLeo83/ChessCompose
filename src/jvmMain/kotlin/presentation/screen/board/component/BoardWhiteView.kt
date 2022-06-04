package presentation.screen.board.component

import androidx.compose.runtime.Composable
import domain.model.Board
import domain.model.Cell

@Composable
fun BoardWhiteView(board: Board) {
    val rows = splitCellsInRows(board)
    BoardView(rows)
}

private fun splitCellsInRows(board: Board): List<List<Cell>> {
    val rows = arrayListOf<List<Cell>>()
    for (row in 8 downTo 1) {
        val sublist = board.cells.subList(8 * (row - 1), 8 * row)
        rows.add(sublist)
    }
    return rows
}