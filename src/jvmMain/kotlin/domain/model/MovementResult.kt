package domain.model

sealed interface MovementResult {
    object EmptyOrigin: MovementResult
    object OriginHasOpponentPiece: MovementResult

    object DestinyOutOfBounds: MovementResult
    object PieceOfSameTeamInDestiny: MovementResult
    object PieceOfOpponentInDestiny: MovementResult
    object Success: MovementResult
}