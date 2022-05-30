package core.model

sealed class Direction(val rowStep: Int, val columnStep: Int) {
    object North: Direction(-1, 0)
    object NorthEast: Direction(-1, 1)
    object East: Direction(0, 1)
    object SouthEast: Direction(1, 1)
    object South: Direction(1, 0)
    object SouthWest: Direction(1, -1)
    object West: Direction(0, -1)
    object NorthWest: Direction(-1, -1)
}