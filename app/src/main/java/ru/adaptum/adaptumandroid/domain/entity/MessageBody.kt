package ru.adaptum.adaptumandroid.domain.entity

data class MessageBody(
    val senderId: Int,
    val recipientId: Int,
    val message: String,
)
