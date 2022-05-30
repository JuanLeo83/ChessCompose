package core.model

data class Board(
    val cells: List<Cell>
)

fun getBoardDimensions() = Pair(8, 8)
