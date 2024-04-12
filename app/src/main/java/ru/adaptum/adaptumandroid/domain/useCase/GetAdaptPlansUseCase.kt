package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.repository.PlansRepository
import javax.inject.Inject

class GetAdaptPlansUseCase
    @Inject
    constructor(
        private val plansRepository: PlansRepository,
    ) {
        suspend operator fun invoke() = plansRepository.getAdaptPlans()
    }
