package data.source.database.model

@kotlinx.serialization.Serializable
data class BoardEntity(
    val cells: List<CellEntity>
)
