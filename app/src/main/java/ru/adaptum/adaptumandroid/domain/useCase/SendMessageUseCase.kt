package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.entity.MessageBody
import ru.adaptum.adaptumandroid.domain.repository.MessagesRepository
import javax.inject.Inject

class SendMessageUseCase
    @Inject
    constructor(private val messagesRepository: MessagesRepository) {
        suspend operator fun invoke(message: MessageBody) {
            messagesRepository.sendMessage(message)
        }
    }
