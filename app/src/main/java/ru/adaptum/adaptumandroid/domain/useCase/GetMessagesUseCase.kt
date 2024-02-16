package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.entity.Message
import ru.adaptum.adaptumandroid.domain.repository.MessagesRepository
import javax.inject.Inject

class GetMessagesUseCase
    @Inject
    constructor(
        private val messagesRepository: MessagesRepository,
    ) {
        suspend operator fun invoke(contactId: Int): List<Message> {
            return messagesRepository.getMessages(contactId)
        }
    }
