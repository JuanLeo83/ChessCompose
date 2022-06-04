package presentation.screen.board.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import domain.model.Cell
import domain.model.Piece
import presentation.screen.common.Theme
import presentation.screen.common.cellSize
import presentation.screen.common.mapPositionToColor

@Composable
fun BoardView(rows: List<List<Cell>>) {
    Column {
        rows.forEach { row ->
            Row {
                row.forEach { cell ->
                    Box(
                        modifier = Modifier
                            .width(cellSize.dp)
                            .height(cellSize.dp)
                            .background(color = mapPositionToColor(Theme.Brown, cell.position))
                    ) {
                        Text(
                            text = cell.name,
                            color = mapPositionToColor(Theme.Brown, cell.position, true),
                            modifier = Modifier.padding(2.dp)
                        )

                        mapPieceToImage(cell.piece)?.let {
                            Image(
                                painter = painterResource(it),
                                contentDescription = it,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(cellSize.dp)
                                    .height(cellSize.dp)
                                    .padding(6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun mapPieceToImage(piece: Piece?): String?{
    return piece?.let {
        "${it.type.name}${it.team.javaClass.simpleName}.png"
    }

}