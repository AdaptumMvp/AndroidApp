package ru.adaptum.adaptumandroid.data.network.dto

import ru.adaptum.adaptumandroid.domain.entity.Token

data class TokenDataDto(val token: String) {
    fun toDomainModel() = Token(token = token)
}
