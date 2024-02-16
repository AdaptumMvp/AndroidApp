package ru.adaptum.adaptumandroid.domain.entity

data class Stage(
    val id: Int,
    val name: String,
    val status: String,
    val description: String,
    val date: String,
    val documentUrl: String,
    val videoUrl: String,
    val userDataOnStageKeys: UserDataOnStageKeys,
)
