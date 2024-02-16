package ru.adaptum.adaptumandroid.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.adaptum.adaptumandroid.domain.entity.UserDataOnStageKeys

data class UserDataOnStageKeysDto(
    @SerializedName("onboarding_event_id") val onboardingEventId: Int,
    @SerializedName("user") val user: Int,
    @SerializedName("user_group") val userGroup: Int,
    @SerializedName("stage") val stage: Int,
) {
    fun toModel() = UserDataOnStageKeys(onboardingEventId, user, userGroup, stage)
}
