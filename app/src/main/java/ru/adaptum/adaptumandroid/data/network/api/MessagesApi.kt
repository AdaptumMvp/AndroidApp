package ru.adaptum.adaptumandroid.data.network.api

import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import ru.adaptum.adaptumandroid.data.network.dto.MessageDto

interface MessagesApi {
    @GET("api/chat/contacts/{contactId}/talks/")
    suspend fun getMessages(
        @Path("contactId") contactId: Int,
    ): List<MessageDto>

    @Multipart
    @POST("/api/chat/")
    suspend fun sendMessage(
        @Part("message") message: RequestBody,
        @Part("sender") sender: Int,
        @Part("recipient") recipient: Int,
    )
}
