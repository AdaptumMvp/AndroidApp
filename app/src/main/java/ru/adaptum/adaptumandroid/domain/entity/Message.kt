package ru.adaptum.adaptumandroid.domain.entity

data class Message(
    val id: Int,
    val messageText: String,
    val isSent: Boolean,
)
