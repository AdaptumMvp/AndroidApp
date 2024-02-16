package ru.adaptum.adaptumandroid.data.network.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.adaptum.adaptumandroid.data.network.dto.TokenDataDto
import ru.adaptum.adaptumandroid.domain.entity.User

interface AuthApi {
    @POST("api-token-auth/")
    suspend fun auth(
        @Body user: User,
    ): TokenDataDto
}
