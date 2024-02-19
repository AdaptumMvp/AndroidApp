package ru.adaptum.adaptumandroid.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.adaptum.adaptumandroid.domain.entity.State
import ru.adaptum.adaptumandroid.domain.entity.User

interface AuthRepository {
    fun auth(user: User): Flow<State<Unit>>

    fun logout(): Flow<State<Unit>>
}
