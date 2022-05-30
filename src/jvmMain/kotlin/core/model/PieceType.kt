package core.model

const val moveUntilObstacle: Int = -1

sealed class PieceType(
    val name: String,
    private val steps: Int,
    val lastStepDirections: List<Pair<Direction, List<Direction>>> = emptyList(),
    val teamIndependentTravelDirections: List<Direction> = emptyList(),
    val teamIndependentAttackDirections: List<Direction> = emptyList(),
    val whiteTravelDirections: List<Direction> = emptyList(),
    val whiteAttackDirections: List<Direction> = emptyList(),
    val blackTravelDirections: List<Direction> = emptyList(),
    val blackAttackDirections: List<Direction> = emptyList(),
) {
    object Pawn : PieceType(
        name = "Pawn",
        steps = 1,
        whiteTravelDirections = listOf(Direction.North),
        whiteAttackDirections = listOf(Direction.NorthWest, Direction.NorthEast),
        blackTravelDirections = listOf(Direction.South),
        blackAttackDirections = listOf(Direction.SouthWest, Direction.SouthEast)
    )

    object Rook : PieceType(
        name = "Rook",
        steps = moveUntilObstacle,
        teamIndependentTravelDirections = listOf(
            Direction.North,
            Direction.East,
            Direction.South,
            Direction.West
        ),
        teamIndependentAttackDirections = listOf(
            Direction.North,
            Direction.East,
            Direction.South,
            Direction.West
        )
    )

    object Knight : PieceType(
        name = "Knight",
        steps = 3,
        lastStepDirections = listOf(
            Pair(Direction.North, listOf(Direction.West, Direction.East)),
            Pair(Direction.East, listOf(Direction.North, Direction.South)),
            Pair(Direction.South, listOf(Direction.West, Direction.East)),
            Pair(Direction.West, listOf(Direction.North, Direction.South)),
        ),
        teamIndependentTravelDirections = listOf(
            Direction.North,
            Direction.East,
            Direction.South,
            Direction.West
        ),
        teamIndependentAttackDirections = listOf(
            Direction.North,
            Direction.East,
            Direction.South,
            Direction.West
        )
    )

    object Bishop : PieceType(
        name = "Bishop",
        steps = moveUntilObstacle,
        teamIndependentTravelDirections = listOf(
            Direction.NorthWest,
            Direction.NorthEast,
            Direction.SouthWest,
            Direction.SouthEast
        ),
        teamIndependentAttackDirections = listOf(
            Direction.NorthWest,
            Direction.NorthEast,
            Direction.SouthWest,
            Direction.SouthEast
        )
    )

    object Queen : PieceType(
        name = "Queen",
        steps = moveUntilObstacle,
        teamIndependentTravelDirections = listOf(
            Direction.North,
            Direction.NorthEast,
            Direction.East,
            Direction.SouthEast,
            Direction.South,
            Direction.SouthWest,
            Direction.West,
            Direction.NorthWest
        ),
        teamIndependentAttackDirections = listOf(
            Direction.North,
            Direction.NorthEast,
            Direction.East,
            Direction.SouthEast,
            Direction.South,
            Direction.SouthWest,
            Direction.West,
            Direction.NorthWest
        )
    )

    object King : PieceType(
        name = "King",
        steps = 1,
        teamIndependentTravelDirections = listOf(
            Direction.North,
            Direction.NorthEast,
            Direction.East,
            Direction.SouthEast,
            Direction.South,
            Direction.SouthWest,
            Direction.West,
            Direction.NorthWest
        ),
        teamIndependentAttackDirections = listOf(
            Direction.North,
            Direction.NorthEast,
            Direction.East,
            Direction.SouthEast,
            Direction.South,
            Direction.SouthWest,
            Direction.West,
            Direction.NorthWest
        )
    )

    fun getTravelDirections(team: Team): List<Direction> {
        return if (teamIndependentTravelDirections.isNotEmpty()) {
            teamIndependentTravelDirections
        } else if (team == Team.White) {
            whiteTravelDirections
        } else {
            blackTravelDirections
        }
    }

    fun getAttackDirections(team: Team): List<Direction> {
        return if (teamIndependentAttackDirections.isNotEmpty()) {
            teamIndependentAttackDirections
        } else if (team == Team.White) {
            whiteAttackDirections
        } else {
            blackAttackDirections
        }
    }

    fun getPieceSteps(isFirstMovement: Boolean = false): Int {
        return if (this is Pawn && isFirstMovement) {
            steps + 1
        } else {
            steps
        }
    }
}
