package presentation.screen.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import domain.model.Team
import org.koin.java.KoinJavaComponent.get
import presentation.screen.board.component.BoardBlackView
import presentation.screen.board.component.BoardWhiteView

@Composable
fun BoardScreen(screenModel: BoardScreenModel = get(BoardScreenModel::class.java)) {
    val state = screenModel.state.collectAsState()

    when (state.value) {
        is BoardScreenState.Loading -> {

        }
        is BoardScreenState.PlayingGame -> {
            val gameStatus = (state.value as BoardScreenState.PlayingGame).gameStatus

            when (gameStatus.playerTeam) {
                is Team.White -> BoardWhiteView(gameStatus.board)
                is Team.Black -> BoardBlackView(gameStatus.board)
            }
        }
        is BoardScreenState.GameFinished -> {

        }
    }
}