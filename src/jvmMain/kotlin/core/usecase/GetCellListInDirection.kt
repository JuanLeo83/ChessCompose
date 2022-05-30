package core.usecase

import core.boundary.BoardRepository
import core.model.Cell
import core.model.Direction
import core.model.Piece
import core.model.PieceType

class GetCellListInDirection(
    private val boardRepository: BoardRepository
) {
    operator fun invoke(
        origin: Cell,
        direction: Direction,
        piece: Piece,
        isFirstMovement: Boolean = false
    ): List<Cell> {
        return if (piece.type is PieceType.Knight) {
            getCellsForKnight(origin, direction, piece.type)
        } else {
            getCellsForPiece(origin, direction, piece, isFirstMovement)
        }
    }

    private fun getCellsForKnight(origin: Cell, direction: Direction, piece: PieceType.Knight): List<Cell> {
        val cells = mutableListOf<Cell>()

        val board = boardRepository.getBoard()

        var nextCellPositionToCheck = sumDirectionToPosition(origin.position, direction)
        nextCellPositionToCheck = sumDirectionToPosition(nextCellPositionToCheck, direction)

        val possibleCellsInDirection = piece.lastStepDirections.find { it.first == direction }
        possibleCellsInDirection?.second?.forEach { directionVariant ->
            val knightPositionToCheck = sumDirectionToPosition(nextCellPositionToCheck, directionVariant)
            val cell = board.cells.find { cell -> cell.position == knightPositionToCheck }
            cell?.let { cells.add(cell) }
        }

        return cells
    }

    private fun getCellsForPiece(
        origin: Cell,
        direction: Direction,
        piece: Piece,
        isFirstMovement: Boolean = false
    ): List<Cell> {
        val cells = mutableListOf<Cell>()

        var stepsRemaining = piece.type.getPieceSteps(isFirstMovement)
        val board = boardRepository.getBoard()

        var nextCellPositionToCheck = sumDirectionToPosition(origin.position, direction)
        do {
            val cell = board.cells.find { cell -> cell.position == nextCellPositionToCheck }
            cell?.let {
                cells.add(cell)
                nextCellPositionToCheck = sumDirectionToPosition(nextCellPositionToCheck, direction)
                stepsRemaining--
            }
        } while (cell != null || stepsRemaining == 0)

        return cells
    }

    private fun sumDirectionToPosition(
        position: Pair<Int, Int>,
        direction: Direction
    ): Pair<Int, Int> {
        return Pair(
            position.first + direction.rowStep,
            position.second + direction.columnStep
        )
    }
}