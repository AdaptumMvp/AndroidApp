package ru.adaptum.adaptumandroid.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.adaptum.adaptumandroid.domain.entity.Token
import ru.adaptum.adaptumandroid.domain.entity.User

interface AuthRepository {
    fun auth(user: User): Flow<Token>

    fun logout()
}
