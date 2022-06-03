package data.source.database.model

@kotlinx.serialization.Serializable
data class PieceEntity(
    val type: String,
    val team: String,
    val isAlive: Boolean,
    val cellName: String?
)
