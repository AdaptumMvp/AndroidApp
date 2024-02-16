package ru.adaptum.adaptumandroid.domain.entity

data class ProfileData(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val job: String,
    val organization: String,
    val mail: String,
    val city: String,
)
