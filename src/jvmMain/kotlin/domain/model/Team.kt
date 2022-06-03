package domain.model

sealed interface Team {
    object Black: Team
    object White: Team
}