package domain.boundary

import domain.model.GameStatus

interface GameRepository {
    fun getCurrentGameStatus(): GameStatus
    fun getMostRecentGameSaved(): GameStatus?
    fun saveGameStatus(gameStatus: GameStatus)
    fun getGameStatus(timestamp: Long): GameStatus?
}