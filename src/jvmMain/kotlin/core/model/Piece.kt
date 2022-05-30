package core.model

data class Piece(
    val type: PieceType,
    val team: Team,
    val isAlive: Boolean,
    val cellName: String
)

val listOfAllPieces = listOf(
    Piece(PieceType.Rook, Team.White, true, "1A"),
    Piece(PieceType.Knight, Team.White, true, "1B"),
    Piece(PieceType.Bishop, Team.White, true, "1C"),
    Piece(PieceType.Queen, Team.White, true, "1D"),
    Piece(PieceType.King, Team.White, true, "1E"),
    Piece(PieceType.Bishop, Team.White, true, "1F"),
    Piece(PieceType.Knight, Team.White, true, "1G"),
    Piece(PieceType.Rook, Team.White, true, "1H"),

    Piece(PieceType.Pawn, Team.White, true, "2A"),
    Piece(PieceType.Pawn, Team.White, true, "2B"),
    Piece(PieceType.Pawn, Team.White, true, "2C"),
    Piece(PieceType.Pawn, Team.White, true, "2D"),
    Piece(PieceType.Pawn, Team.White, true, "2E"),
    Piece(PieceType.Pawn, Team.White, true, "2F"),
    Piece(PieceType.Pawn, Team.White, true, "2G"),
    Piece(PieceType.Pawn, Team.White, true, "2H"),

    Piece(PieceType.Pawn, Team.Black, true, "7A"),
    Piece(PieceType.Pawn, Team.Black, true, "7B"),
    Piece(PieceType.Pawn, Team.Black, true, "7C"),
    Piece(PieceType.Pawn, Team.Black, true, "7D"),
    Piece(PieceType.Pawn, Team.Black, true, "7E"),
    Piece(PieceType.Pawn, Team.Black, true, "7F"),
    Piece(PieceType.Pawn, Team.Black, true, "7G"),
    Piece(PieceType.Pawn, Team.Black, true, "7H"),

    Piece(PieceType.Rook, Team.Black, true, "8A"),
    Piece(PieceType.Knight, Team.Black, true, "8B"),
    Piece(PieceType.Bishop, Team.Black, true, "8C"),
    Piece(PieceType.Queen, Team.Black, true, "8D"),
    Piece(PieceType.King, Team.Black, true, "8E"),
    Piece(PieceType.Bishop, Team.Black, true, "8F"),
    Piece(PieceType.Knight, Team.Black, true, "8G"),
    Piece(PieceType.Rook, Team.Black, true, "8H")
)