package domain.usecase

import domain.model.Cell
import domain.model.MovementResult
import domain.model.Movements
import domain.model.Team
import utils.Either

class GetListOfMovementsUseCase(
    private val checkOriginCellUseCase: CheckOriginCellUseCase,
    private val getTravelMovementListUseCase: GetTravelMovementListUseCase,
    private val getAttackMovementListUseCase: GetAttackMovementListUseCase
) {
    operator fun invoke(timestamp: Long, playerTeam: Team, origin: Cell): Either<Movements, MovementResult> {
        val checkOriginResult = checkOriginCellUseCase(playerTeam, origin)

        return if (checkOriginResult == MovementResult.Success) {
            Either.Success(getListOfMovements(timestamp, playerTeam, origin))
        } else {
            Either.Failure(checkOriginResult)
        }
    }

    private fun getListOfMovements(timestamp: Long, playerTeam: Team, origin: Cell): Movements {
        return origin.piece?.let {
            val travelResult = getTravelMovementListUseCase(timestamp, playerTeam, origin)
            val attackResult = getAttackMovementListUseCase(timestamp, playerTeam, origin)

            var travelCells = listOf<Cell>()
            var attackCells = listOf<Cell>()

            if (travelResult is Either.Success) { travelCells = travelResult.data }

            if (attackResult is Either.Success) { attackCells = attackResult.data }

            Movements(travelCells, attackCells)
        } ?: Movements()
    }
}