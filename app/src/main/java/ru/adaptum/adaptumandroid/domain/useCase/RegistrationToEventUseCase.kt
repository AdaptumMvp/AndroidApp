package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.repository.EventsRepository
import javax.inject.Inject

class RegistrationToEventUseCase
    @Inject
    constructor(
        private val eventsRepository: EventsRepository,
    ) {
        suspend operator fun invoke(eventId: Int) = eventsRepository.registrationToEvent(eventId)
    }
