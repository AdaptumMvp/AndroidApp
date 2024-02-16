package ru.adaptum.adaptumandroid.domain.repository

import ru.adaptum.adaptumandroid.domain.entity.ProfileData

interface ProfileDataRepository {
    suspend fun getProfileData(): ProfileData
}
