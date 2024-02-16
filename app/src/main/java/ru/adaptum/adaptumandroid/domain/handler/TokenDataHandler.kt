package ru.adaptum.adaptumandroid.domain.handler

import ru.adaptum.adaptumandroid.domain.entity.Token

interface TokenDataHandler {
    fun saveToken(token: Token)

    fun getToken(): Token

    fun removeToken()

    fun isTokenEmpty(): Boolean

    fun isTokenNotEmpty(): Boolean
}
