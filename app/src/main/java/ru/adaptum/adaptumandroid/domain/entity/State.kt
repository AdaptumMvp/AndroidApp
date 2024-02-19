package ru.adaptum.adaptumandroid.domain.entity

sealed class State<out T> {
    data object Loading : State<Nothing>()

    class Failure(val msg: Throwable) : State<Nothing>()

    class Success<T>(val data: T) : State<T>()

    data object Empty : State<Nothing>()
}
