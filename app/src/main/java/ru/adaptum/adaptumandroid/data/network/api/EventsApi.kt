package ru.adaptum.adaptumandroid.data.network.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.adaptum.adaptumandroid.data.network.dto.EventDto
import ru.adaptum.adaptumandroid.data.network.dto.RegistrationToEventBody

interface EventsApi {
    @GET("api/events/all/")
    suspend fun getEvents(): List<EventDto>

    @POST("api/events/registration/")
    suspend fun registrationToEvent(
        @Body registrationToEventBody: RegistrationToEventBody,
    )
}
