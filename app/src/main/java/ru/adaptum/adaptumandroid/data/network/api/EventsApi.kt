package ru.adaptum.adaptumandroid.data.network.api

import retrofit2.http.GET
import ru.adaptum.adaptumandroid.data.network.dto.EventDto

interface EventsApi {
    @GET("api/events/all/")
    suspend fun getEvents(): List<EventDto>
}
