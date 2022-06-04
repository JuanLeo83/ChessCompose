package data.source.database.map

import data.source.database.model.BoardEntity
import data.source.database.model.CellEntity
import data.source.database.model.GameStatusEntity
import data.source.database.model.PieceEntity
import domain.model.Board
import domain.model.Cell
import domain.model.GameStatus
import domain.model.Piece

fun mapGameStatusToEntity(gameStatus: GameStatus): GameStatusEntity {
    return GameStatusEntity(
        timestamp = gameStatus.timestamp,
        board = mapBoardToEntity(gameStatus.board),
        playerTeam = gameStatus.playerTeam.toString(),
        whitePiecesRemaining = gameStatus.whitePiecesInBoard,
        blackPiecesRemaining = gameStatus.blackPiecesInBoard,
        currentTeamPlaying = gameStatus.currentTeamPlaying.toString(),
        whiteTeamTimeRemaining = gameStatus.whiteTeamTimeRemaining,
        blackTeamTimeRemaining = gameStatus.blackTeamTimeRemaining,
        winner = gameStatus.winner?.toString()
    )
}

fun mapBoardToEntity(board: Board): BoardEntity {
    return BoardEntity(
        cells = mapCellListToEntity(board.cells)
    )
}

fun mapCellListToEntity(cellList: List<Cell>): List<CellEntity> {
    return cellList.map { mapCellToEntity(it) }
}

fun mapCellToEntity(cell: Cell): CellEntity {
    return CellEntity(
        name = cell.name,
        row = cell.position.first,
        column = cell.position.second,
        piece = cell.piece?.let { mapPieceToEntity(it) }
    )
}

fun mapPieceToEntity(piece: Piece): PieceEntity {
    return PieceEntity(
        type = piece.type.name,
        team = piece.team.toString(),
        isAlive = piece.isAlive,
        cellName = piece.cellName
    )
}