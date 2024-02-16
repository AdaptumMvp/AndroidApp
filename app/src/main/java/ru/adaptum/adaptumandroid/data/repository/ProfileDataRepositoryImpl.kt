package ru.adaptum.adaptumandroid.data.repository

import ru.adaptum.adaptumandroid.data.network.api.ProfileDataApi
import ru.adaptum.adaptumandroid.domain.entity.ProfileData
import ru.adaptum.adaptumandroid.domain.repository.ProfileDataRepository
import javax.inject.Inject

class ProfileDataRepositoryImpl
    @Inject
    constructor(
        private val profileDataApi: ProfileDataApi,
    ) : ProfileDataRepository {
        override suspend fun getProfileData(): ProfileData {
            return profileDataApi.getProfileData().first().toModel()
        }
    }
