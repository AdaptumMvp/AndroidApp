package ru.adaptum.adaptumandroid.domain.repository

import ru.adaptum.adaptumandroid.domain.entity.Event

interface EventsRepository {
    suspend fun getEvents(): List<Event>

    suspend fun registrationToEvent(eventId: Int)
}
