package core.model

data class GameStatus(
    val whitePiecesInBoard: Int,
    val blackPiecesInBoard: Int,
    var currentTeamPlaying: Team,
    var whiteTeamTimeRemaining: Int,
    var blackTeamTimeRemaining: Int,
    var winner: Team? = null
)