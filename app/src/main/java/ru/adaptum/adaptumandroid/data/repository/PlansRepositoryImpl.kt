package ru.adaptum.adaptumandroid.data.repository

import ru.adaptum.adaptumandroid.data.network.api.AdaptListApi
import ru.adaptum.adaptumandroid.domain.entity.AdaptPlan
import ru.adaptum.adaptumandroid.domain.entity.Stage
import ru.adaptum.adaptumandroid.domain.entity.UserDataOnStageKeys
import ru.adaptum.adaptumandroid.domain.repository.PlansRepository
import javax.inject.Inject

class PlansRepositoryImpl
    @Inject
    constructor(
        private val adaptListApi: AdaptListApi,
    ) : PlansRepository {
        override suspend fun getAdaptPlans(): List<AdaptPlan> {
            return adaptListApi.getAdaptPlans().map { it.toModel() }
        }

        override suspend fun getStages(groupId: Int): List<Stage> {
            return adaptListApi.getStages(groupId).map { it.toModel() }
        }

        override suspend fun completeStage(
            timeSpent: Int,
            userDataOnStageKeys: UserDataOnStageKeys,
        ) {
            adaptListApi.completeStage(
                id = userDataOnStageKeys.onboardingEventId,
                user = userDataOnStageKeys.user,
                userGroup = userDataOnStageKeys.userGroup,
                stage = userDataOnStageKeys.stage,
                timeSpent = timeSpent,
            )
        }
    }
