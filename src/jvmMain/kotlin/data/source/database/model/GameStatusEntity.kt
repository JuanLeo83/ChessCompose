package data.source.database.model

import org.kodein.db.model.Id

@kotlinx.serialization.Serializable
data class GameStatusEntity(
    @Id val timestamp: Long,
    val board: BoardEntity,
    val whitePiecesRemaining: Int,
    val blackPiecesRemaining: Int,
    val currentTeamPlaying: String,
    val whiteTeamTimeRemaining: Long,
    val blackTeamTimeRemaining: Long,
    val winner: String? = null
)
