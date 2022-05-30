package core.model

data class Movements(
    val travel: List<Cell> = emptyList(),
    val attack: List<Cell> = emptyList()
)