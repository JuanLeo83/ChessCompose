package core.usecase

import core.model.*
import utils.Either

class GetTravelMovementListUseCase(
    private val getCellListInDirection: GetCellListInDirection,
    private val checkMovementPossibleUseCase: CheckMovementPossibleUseCase
) {
    operator fun invoke(playerTeam: Team, origin: Cell): Either<List<Cell>, MovementResult> {
        return origin.piece?.let { piece ->
            val movements = mutableListOf<Cell>()

            val travelDirections = piece.type.getTravelDirections(playerTeam)
            travelDirections.forEach { direction ->
                movements.addAll(getMovements(playerTeam, origin, direction, piece))
            }

            Either.Success(movements)
        } ?: Either.Failure(MovementResult.EmptyOrigin)
    }

    private fun getMovements(
        playerTeam: Team,
        origin: Cell,
        direction: Direction,
        piece: Piece
    ): List<Cell> {
        val movements = mutableListOf<Cell>()

        getCellListInDirection(origin, direction, piece).forEach breaking@ { cell ->
            if (checkMovementPossibleUseCase(playerTeam, origin, cell) is MovementResult.Success) {
                movements.add(cell)
            } else if (piece.type !is PieceType.Knight) {
                return@breaking
            }
        }

        return movements
    }
}

