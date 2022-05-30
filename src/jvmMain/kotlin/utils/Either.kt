package utils

sealed class Either<out T, out E> {
    data class Success<out T>(val data: T) : Either<T, Nothing>()
    data class Failure<out E>(val data: E) : Either<Nothing, E>()
}

fun successEmpty() = Either.Success(Unit)

fun <T> success(data: T) = Either.Success(data)

fun <E> failure(error: E) = Either.Failure(error)
