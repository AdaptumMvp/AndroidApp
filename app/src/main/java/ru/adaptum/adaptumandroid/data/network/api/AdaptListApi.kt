package ru.adaptum.adaptumandroid.data.network.api

import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import ru.adaptum.adaptumandroid.data.network.dto.AdaptPlanDto
import ru.adaptum.adaptumandroid.data.network.dto.StageDto

interface AdaptListApi {
    @GET("api/onboarding/users/my/adapt/list")
    suspend fun getAdaptPlans(): List<AdaptPlanDto>

    @GET("api/onboarding/users/my/user-group/{groupId}/stages")
    suspend fun getStages(
        @Path("groupId") groupId: Int,
    ): List<StageDto>

    @Multipart
    @PUT("api/onboarding/stages/events/{id}/")
    suspend fun completeStage(
        @Path("id") id: Int,
        @Part("user") user: Int,
        @Part("user_group") userGroup: Int,
        @Part("stage") stage: Int,
        @Part("time_spent") timeSpent: Int,
        @Part("is_confirmed") isConfirmed: Boolean = true,
    )
}
