package domain.usecase

import domain.boundary.GameRepository
import domain.model.*

class PrepareGameUseCase(
    private val gameRepository: GameRepository
) {
    private val totalRows = getBoardDimensions().first
    private val totalColumns = getBoardDimensions().second

    operator fun invoke(): GameStatus {
        val gameStatus = GameStatus(
            timestamp = System.currentTimeMillis(),
            board = Board(getCellList()),
            whitePiecesInBoard = 16,
            blackPiecesInBoard = 16,
            currentTeamPlaying = Team.White,
            whiteTeamTimeRemaining = 0,
            blackTeamTimeRemaining = 0
        )
        gameRepository.saveGameStatus(gameStatus)
        return gameStatus
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