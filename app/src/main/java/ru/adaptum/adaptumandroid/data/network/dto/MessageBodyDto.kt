package ru.adaptum.adaptumandroid.data.network.dto

import ru.adaptum.adaptumandroid.domain.entity.MessageBody

data class MessageBodyDto(
    val message: String,
    val sender: Int,
    val recipient: Int,
) {
    companion object {
        fun fromModel(message: MessageBody) =
            MessageBodyDto(
                message = message.message,
                sender = message.senderId,
                recipient = message.recipientId,
            )
    }
}
