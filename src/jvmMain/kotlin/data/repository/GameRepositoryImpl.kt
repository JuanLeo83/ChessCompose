package data.repository

import data.source.cache.CurrentGameStatusCache
import data.source.database.DatabaseManager
import data.source.database.map.mapGameStatusToDomain
import data.source.database.map.mapGameStatusToEntity
import domain.boundary.GameRepository
import domain.model.GameStatus

class GameRepositoryImpl : GameRepository {

    private val dbManager by lazy { DatabaseManager() }

    override fun getCurrentGameStatus(): GameStatus {
        return mapGameStatusToDomain(CurrentGameStatusCache.currentGameStatus!!)
    }

    override fun getMostRecentGameSaved(): GameStatus? {
        val mostRecentGame = dbManager.getMostRecentGame()
        CurrentGameStatusCache.currentGameStatus = mostRecentGame
        return mostRecentGame?.let { mapGameStatusToDomain(it) }
    }

    override fun saveGameStatus(gameStatus: GameStatus) {
        val gameStatusEntity = mapGameStatusToEntity(gameStatus)
        CurrentGameStatusCache.currentGameStatus = gameStatusEntity
        dbManager.saveGameStatus(gameStatusEntity)
    }

    override fun getGameStatus(timestamp: Long): GameStatus? {
        val gameStatusEntity = dbManager.getGameStatus(timestamp)
        CurrentGameStatusCache.currentGameStatus = gameStatusEntity
        return gameStatusEntity?.let { mapGameStatusToDomain(it) }
    }

}