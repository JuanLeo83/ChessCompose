package core.boundary

import core.model.Board

interface BoardRepository {
    fun saveBoard(board: Board)
    fun getBoard(): Board
}