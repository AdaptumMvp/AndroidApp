package ru.adaptum.adaptumandroid.presentation.diffUtils

interface ListItem<T> {
    val id: Int
    val content: T

    fun contentEquals(item: ListItem<T>): Boolean
}
