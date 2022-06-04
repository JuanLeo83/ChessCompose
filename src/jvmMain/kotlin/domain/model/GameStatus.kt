package domain.model

data class GameStatus(
    val timestamp: Long,
    val board: Board,
    val playerTeam: Team,
    val whitePiecesInBoard: Int,
    val blackPiecesInBoard: Int,
    var currentTeamPlaying: Team,
    var whiteTeamTimeRemaining: Long,
    var blackTeamTimeRemaining: Long,
    var winner: Team? = null
)