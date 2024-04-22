package ru.adaptum.adaptumandroid.presentation.model

import ru.adaptum.adaptumandroid.domain.entity.ProfileData

data class ProfileDataUI(
    val avatarUrl: String,
    val name: String,
    val job: String,
    val organization: String,
    val mail: String,
    val city: String,
) {
    companion object {

        fun fromProfileData(profileData: ProfileData) =
            ProfileDataUI(
                avatarUrl = profileData.avatarUrl,
                name = profileData.name,
                job = profileData.job,
                organization = profileData.organization,
                mail = profileData.mail,
                city = profileData.city,
            )

        fun empty() = ProfileDataUI(
            avatarUrl = "",
            name = "",
            job = "",
            organization = "",
            mail = "",
            city = "",
        )
    }
}
