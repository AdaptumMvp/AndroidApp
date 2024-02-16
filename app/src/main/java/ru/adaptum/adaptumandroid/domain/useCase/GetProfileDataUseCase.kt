package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.repository.ProfileDataRepository
import javax.inject.Inject

class GetProfileDataUseCase
    @Inject
    constructor(
        private val profileDataRepository: ProfileDataRepository,
    ) {
        suspend operator fun invoke() = profileDataRepository.getProfileData()
    }
