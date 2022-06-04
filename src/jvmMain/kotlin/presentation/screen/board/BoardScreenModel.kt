package presentation.screen.board

import domain.model.GameStatus
import domain.model.Team
import domain.usecase.PrepareGameUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class BoardScreenModel(
    prepareGameUseCase: PrepareGameUseCase
) {

    var state = MutableStateFlow<BoardScreenState>(BoardScreenState.Loading)
        private set

    init {
        val gameStatus = prepareGameUseCase(Team.Black)
        state.value = BoardScreenState.PlayingGame(gameStatus)
    }

}

sealed class BoardScreenState(gameStatus: GameStatus? = null) {
    object Loading : BoardScreenState()
    data class PlayingGame(val gameStatus: GameStatus) : BoardScreenState(gameStatus)
    data class GameFinished(val gameStatus: GameStatus) : BoardScreenState(gameStatus)
}