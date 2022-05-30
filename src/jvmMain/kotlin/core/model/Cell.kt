package core.model

data class Cell(
    val name: String,
    val position: Pair<Int,  Int>,
    var piece: Piece? = null
)
