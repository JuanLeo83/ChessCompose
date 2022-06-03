package domain.usecase

import domain.model.Cell
import domain.model.MovementResult
import domain.model.Team

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