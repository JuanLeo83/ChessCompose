package core.usecase

import core.model.*
import utils.Either

class GetAttackMovementListUseCase(
    private val getCellListInDirection: GetCellListInDirection,
    private val checkMovementPossibleUseCase: CheckMovementPossibleUseCase
) {
    operator fun invoke(playerTeam: Team, origin: Cell): Either<List<Cell>, MovementResult> {
        return origin.piece?.let { piece ->
            val movements = mutableListOf<Cell>()

            val attackDirections = piece.type.getAttackDirections(playerTeam)
            attackDirections.forEach { direction ->
                movements.addAll(getAttackMovements(playerTeam, origin, direction, piece))
            }

            Either.Success(movements)
        } ?: Either.Failure(MovementResult.EmptyOrigin)
    }

    private fun getAttackMovements(
        playerTeam: Team,
        origin: Cell,
        direction: Direction,
        piece: Piece
    ): List<Cell> {
        val movements = mutableListOf<Cell>()

        getCellListInDirection(origin, direction, piece).forEach breaking@ { cell ->
            if (checkMovementPossibleUseCase(playerTeam, origin, cell) is MovementResult.PieceOfOpponentInDestiny) {
                movements.add(cell)
                return@breaking
            } else if (
                checkMovementPossibleUseCase(playerTeam, origin, cell) is MovementResult.PieceOfSameTeamInDestiny &&
                piece.type !is PieceType.Knight
            ) {
                return@breaking
            }
        }

        return movements
    }
}