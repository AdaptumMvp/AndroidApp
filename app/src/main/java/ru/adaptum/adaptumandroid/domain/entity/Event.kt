package ru.adaptum.adaptumandroid.domain.entity

data class Event(
    val id: Int,
    val photoUrl: String,
    val status: String,
    val title: String,
    val description: String,
    val date: String,
)
