package ru.adaptum.adaptumandroid.data.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.adaptum.adaptumandroid.data.network.api.AuthApi
import ru.adaptum.adaptumandroid.di.modules.NoAuthInterceptorApi
import ru.adaptum.adaptumandroid.domain.entity.State
import ru.adaptum.adaptumandroid.domain.entity.Token
import ru.adaptum.adaptumandroid.domain.entity.User
import ru.adaptum.adaptumandroid.domain.handler.TokenDataHandler
import ru.adaptum.adaptumandroid.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        @NoAuthInterceptorApi
        private val authApi: AuthApi,
        private val tokenDataHandler: TokenDataHandler,
    ) : AuthRepository {
        private fun saveTokenData(token: Token) {
            tokenDataHandler.saveToken(token)
        }

        override fun auth(user: User) =
            flow {
                emit(State.Loading)
                saveTokenData(authApi.auth(user).toDomainModel())
                emit(State.Success(Unit))
            }.catch {
                emit(State.Failure(it.fillInStackTrace()))
            }

        override fun logout() =
            flow {
                emit(State.Loading)
                tokenDataHandler.removeToken()
                emit(State.Success(Unit))
            }.catch {
                emit(State.Failure(it.fillInStackTrace()))
            }
    }
