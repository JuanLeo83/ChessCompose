package core.usecase

import core.model.*

class PrepareGameUseCase {
    private val totalRows = getBoardDimensions().first
    private val totalColumns = getBoardDimensions().second

    operator fun invoke(): Board {
        return Board(getCellList())
    }

    private fun getCellList(): List<Cell> {
        val cellList = mutableListOf<Cell>()

        for(row in 0 until totalRows) {
            for (column in 0 until totalColumns) {
                val cell = createCellAtPosition(Pair(row, column))
                cellList.add(cell)
            }
        }

        return cellList
    }

    private fun createCellAtPosition(position: Pair<Int, Int>): Cell {
        val cellName = "${getRowNameList(position.first)}${getColumnNameList(position.second)}"
        val piece = getPieceByCellName(cellName)
        return Cell(cellName, position, piece)
    }

    private fun getRowNameList(position: Int): Char {
        val rowNames = "12345678"
        return rowNames[position]
    }

    private fun getColumnNameList(position: Int): Char {
        val columnNames = "ABCDEFGH"
        return columnNames[position]
    }

    private fun getPieceByCellName(cellName: String): Piece? {
        return listOfAllPieces.find { piece -> piece.cellName == cellName }
    }
}