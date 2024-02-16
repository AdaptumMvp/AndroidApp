package ru.adaptum.adaptumandroid.data.network.api

import retrofit2.http.GET
import ru.adaptum.adaptumandroid.data.network.dto.ProfileDataDto

interface ProfileDataApi {
    @GET("/api/myprofile/")
    suspend fun getProfileData(): List<ProfileDataDto>
}
