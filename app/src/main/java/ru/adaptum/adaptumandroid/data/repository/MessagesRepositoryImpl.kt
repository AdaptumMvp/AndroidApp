package ru.adaptum.adaptumandroid.data.repository

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import ru.adaptum.adaptumandroid.data.network.api.MessagesApi
import ru.adaptum.adaptumandroid.domain.entity.Message
import ru.adaptum.adaptumandroid.domain.entity.MessageBody
import ru.adaptum.adaptumandroid.domain.repository.MessagesRepository
import javax.inject.Inject

class MessagesRepositoryImpl
    @Inject
    constructor(
        private val messagesApi: MessagesApi,
    ) : MessagesRepository {
        override suspend fun getMessages(contactId: Int): List<Message> {
            return messagesApi.getMessages(contactId).map { it.toModel() }
        }

        override suspend fun sendMessage(message: MessageBody) {
            messagesApi.sendMessage(
                message = message.message.toRequestBody("text/plain".toMediaTypeOrNull()),
                sender = message.senderId,
                recipient = message.recipientId,
            )
        }
    }
