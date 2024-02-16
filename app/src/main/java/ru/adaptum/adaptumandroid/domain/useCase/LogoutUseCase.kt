package ru.adaptum.adaptumandroid.domain.useCase

import ru.adaptum.adaptumandroid.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        operator fun invoke() = authRepository.logout()
    }
