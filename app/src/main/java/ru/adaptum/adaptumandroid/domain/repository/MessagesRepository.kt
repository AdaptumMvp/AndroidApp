package ru.adaptum.adaptumandroid.domain.repository

import ru.adaptum.adaptumandroid.domain.entity.Message
import ru.adaptum.adaptumandroid.domain.entity.MessageBody

interface MessagesRepository {
    suspend fun getMessages(contactId: Int): List<Message>

    suspend fun sendMessage(message: MessageBody)
}
