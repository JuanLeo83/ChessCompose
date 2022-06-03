package data.source.database

import data.source.database.model.GameStatusEntity
import org.kodein.db.DB
import org.kodein.db.asModelSequence
import org.kodein.db.find
import org.kodein.db.getById
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer

class DatabaseManager {

    private val db: DB by lazy {
        DB.open(
            "./chess.db",
            KotlinxSerializer {
                +GameStatusEntity.serializer()
            }
        )
    }

    fun getMostRecentGame(): GameStatusEntity? {
        var mostRecentGame: GameStatusEntity?

        db.find<GameStatusEntity>().all().use { cursor ->
            mostRecentGame = cursor.asModelSequence()
                .sortedByDescending { it.timestamp }
                .firstOrNull()
        }

        return mostRecentGame
    }

    fun saveGameStatus(gameStatusEntity: GameStatusEntity) {
        db.put(gameStatusEntity)
    }

    fun getGameStatus(id: Long): GameStatusEntity? {
        return db.getById(id)
    }

}