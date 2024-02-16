package ru.adaptum.adaptumandroid.data.repository

import ru.adaptum.adaptumandroid.data.network.api.EventsApi
import ru.adaptum.adaptumandroid.domain.entity.Event
import ru.adaptum.adaptumandroid.domain.repository.EventsRepository
import javax.inject.Inject

class EventsRepositoryImpl
    @Inject
    constructor(
        private val eventsApi: EventsApi,
    ) : EventsRepository {
        override suspend fun getEvents(): List<Event> {
            return eventsApi.getEvents().map { it.toModel() }
        }
    }
