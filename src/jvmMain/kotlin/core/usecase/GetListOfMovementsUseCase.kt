package core.usecase

import core.model.Cell
import core.model.MovementResult
import core.model.Movements
import core.model.Team
import utils.Either

class GetListOfMovementsUseCase(
    private val checkOriginCellUseCase: CheckOriginCellUseCase,
    private val getTravelMovementListUseCase: GetTravelMovementListUseCase,
    private val getAttackMovementListUseCase: GetAttackMovementListUseCase
) {
    operator fun invoke(playerTeam: Team, origin: Cell): Either<Movements, MovementResult> {
        val checkOriginResult = checkOriginCellUseCase(playerTeam, origin)

        return if (checkOriginResult == MovementResult.Success) {
            Either.Success(getListOfMovements(playerTeam, origin))
        } else {
            Either.Failure(checkOriginResult)
        }
    }

    private fun getListOfMovements(playerTeam: Team, origin: Cell): Movements {
        return origin.piece?.let {
            val travelResult = getTravelMovementListUseCase(playerTeam, origin)
            val attackResult = getAttackMovementListUseCase(playerTeam, origin)

            var travelCells = listOf<Cell>()
            var attackCells = listOf<Cell>()

            if (travelResult is Either.Success) { travelCells = travelResult.data }

            if (attackResult is Either.Success) { attackCells = attackResult.data }

            Movements(travelCells, attackCells)
        } ?: Movements()
    }
}