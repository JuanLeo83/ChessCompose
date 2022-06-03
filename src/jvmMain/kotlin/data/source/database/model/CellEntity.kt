package data.source.database.model

@kotlinx.serialization.Serializable
data class CellEntity(
    val name: String,
    val row: Int,
    val column: Int,
    val piece: PieceEntity? = null
)
