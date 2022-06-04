package data.source.database.map

import data.source.database.model.*
import domain.model.*

fun mapGameStatusToDomain(gameStatusEntity: GameStatusEntity): GameStatus {
    return GameStatus(
        board = mapBoardToDomain(gameStatusEntity.board),
        playerTeam = mapTeamToDomain(gameStatusEntity.playerTeam),
        whitePiecesInBoard = gameStatusEntity.whitePiecesRemaining,
        blackPiecesInBoard = gameStatusEntity.blackPiecesRemaining,
        currentTeamPlaying = mapTeamToDomain(gameStatusEntity.currentTeamPlaying),
        whiteTeamTimeRemaining = gameStatusEntity.whiteTeamTimeRemaining,
        blackTeamTimeRemaining = gameStatusEntity.blackTeamTimeRemaining,
        timestamp = gameStatusEntity.timestamp,
        winner = gameStatusEntity.winner?.let { winner -> mapTeamToDomain(winner) }
    )
}

fun mapBoardToDomain(boardEntity: BoardEntity): Board {
    return Board(
        cells = mapCellListToDomain(boardEntity.cells)
    )
}

fun mapCellListToDomain(cellEntityList: List<CellEntity>): List<Cell> {
    return cellEntityList.map { mapCellToDomain(it) }
}

fun mapCellToDomain(cellEntity: CellEntity): Cell {
    return Cell(
        name = cellEntity.name,
        position = Pair(cellEntity.row, cellEntity.column),
        piece = cellEntity.piece?.let { mapPieceToDomain(it) }
    )
}

fun mapPieceToDomain(pieceEntity: PieceEntity): Piece {
    return Piece(
        type = mapPieceType(pieceEntity.type),
        team = mapTeamToDomain(pieceEntity.team),
        isAlive = pieceEntity.isAlive,
        cellName = pieceEntity.cellName
    )
}

fun mapPieceType(typeName: String): PieceType {
    return when (typeName) {
        PieceType.Rook.name -> PieceType.Rook
        PieceType.Knight.name -> PieceType.Knight
        PieceType.Bishop.name -> PieceType.Bishop
        PieceType.Queen.name -> PieceType.Queen
        PieceType.King.name -> PieceType.King
        PieceType.Pawn.name -> PieceType.Pawn
        else -> PieceType.Pawn
    }
}

fun mapTeamToDomain(team: String): Team {
    return when (team) {
        Team.White.toString() -> Team.White
        Team.Black.toString() -> Team.Black
        else -> Team.White
    }
}