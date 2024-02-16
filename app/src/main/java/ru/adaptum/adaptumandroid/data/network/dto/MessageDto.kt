package ru.adaptum.adaptumandroid.data.network.dto

import ru.adaptum.adaptumandroid.domain.entity.Message

data class MessageDto(
    val id: Int,
    val message: String,
    val isMyMessage: Boolean,
) {
    fun toModel() = Message(id, message, isMyMessage)
}
