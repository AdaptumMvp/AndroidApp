package ru.adaptum.adaptumandroid.data.repository

import kotlinx.coroutines.flow.flow
import ru.adaptum.adaptumandroid.data.network.api.AuthApi
import ru.adaptum.adaptumandroid.domain.entity.Token
import ru.adaptum.adaptumandroid.domain.entity.User
import ru.adaptum.adaptumandroid.domain.handler.TokenDataHandler
import ru.adaptum.adaptumandroid.domain.repository.AuthRepository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl
    @Inject
    constructor(
        @Named("NoAuthInterceptor")
        private val authApi: AuthApi,
        private val tokenDataHandler: TokenDataHandler,
    ) : AuthRepository {
        private fun saveTokenData(token: Token) {
            tokenDataHandler.saveToken(token)
        }

        override fun auth(user: User) =
            flow {
                try {
                    val tokenDataDto = authApi.auth(user)
                    saveTokenData(tokenDataDto.toDomainModel())
                    emit(tokenDataDto.toDomainModel())
                } catch (e: Exception) {
                    emit(Token(""))
                }
            }

        override fun logout() {
            tokenDataHandler.removeToken()
        }
    }
