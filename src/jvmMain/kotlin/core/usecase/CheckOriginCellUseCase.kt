package core.usecase

import core.model.Cell
import core.model.MovementResult
import core.model.Team

class CheckOriginCellUseCase {
    operator fun invoke(team: Team, cell: Cell): MovementResult {
        return cell.piece?.let {
            if (it.team != team) {
                MovementResult.OriginHasOpponentPiece
            } else {
                MovementResult.Success
            }
        } ?: MovementResult.EmptyOrigin
    }
}