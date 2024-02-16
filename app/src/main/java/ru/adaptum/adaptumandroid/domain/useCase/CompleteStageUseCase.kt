package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.entity.UserDataOnStageKeys
import ru.adaptum.adaptumandroid.domain.repository.PlansRepository
import javax.inject.Inject

class CompleteStageUseCase
    @Inject
    constructor(
        private val plansRepository: PlansRepository,
    ) {
        suspend fun invoke(
            timeSpent: Int,
            userDataOnStageKeys: UserDataOnStageKeys,
        ) = plansRepository.completeStage(timeSpent, userDataOnStageKeys)
    }
