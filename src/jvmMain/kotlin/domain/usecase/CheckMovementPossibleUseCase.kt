package domain.usecase

import domain.model.Cell
import domain.model.MovementResult
import domain.model.Team
import domain.model.getBoardDimensions

class CheckMovementPossibleUseCase(
    private val checkOriginCellUseCase: CheckOriginCellUseCase
) {
    operator fun invoke(playerTeam: Team, origin: Cell, destiny: Cell): MovementResult {
        return if (!isDestinyInsideBoard(destiny)) {
            MovementResult.DestinyOutOfBounds
        } else if (hasDestinyATeamPiece(playerTeam, destiny)) {
            MovementResult.PieceOfSameTeamInDestiny
        } else if(hasDestinyAnOpponentPiece(playerTeam, destiny)) {
            MovementResult.PieceOfOpponentInDestiny
        } else {
            checkOriginCellUseCase(playerTeam, origin)
        }
    }

    private fun isDestinyInsideBoard(destiny: Cell): Boolean {
        return destiny.position.first >= 0 &&
                destiny.position.first < getBoardDimensions().first &&
                destiny.position.second >= 0 &&
                destiny.position.second < getBoardDimensions().second
    }

    private fun hasDestinyATeamPiece(playerTeam: Team, destiny: Cell): Boolean {
        return destiny.piece?.team == playerTeam
    }

    private fun hasDestinyAnOpponentPiece(playerTeam: Team, destiny: Cell): Boolean {
        return destiny.piece?.team != playerTeam
    }
}