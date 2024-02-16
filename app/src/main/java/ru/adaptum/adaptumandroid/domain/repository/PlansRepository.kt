package ru.adaptum.adaptumandroid.domain.repository

import ru.adaptum.adaptumandroid.domain.entity.AdaptPlan
import ru.adaptum.adaptumandroid.domain.entity.Stage
import ru.adaptum.adaptumandroid.domain.entity.UserDataOnStageKeys

interface PlansRepository {
    suspend fun getAdaptPlans(): List<AdaptPlan>

    suspend fun getStages(groupId: Int): List<Stage>

    suspend fun completeStage(
        timeSpent: Int,
        userDataOnStageKeys: UserDataOnStageKeys,
    )
}
