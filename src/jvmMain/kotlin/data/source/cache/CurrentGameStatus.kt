package data.source.cache

import data.source.database.model.GameStatusEntity

object CurrentGameStatusCache {
    var currentGameStatus: GameStatusEntity? = null
}