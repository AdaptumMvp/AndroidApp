package ru.adaptum.adaptumandroid.presentation.model

import ru.adaptum.adaptumandroid.domain.entity.Stage
import ru.adaptum.adaptumandroid.domain.entity.UserDataOnStageKeys

data class StageListItem(
    val id: Int,
    val name: String,
    val status: String,
    val description: String,
    val date: String,
    val documentUrl: String,
    val videoUrl: String,
    val userDataOnStageKeys: UserDataOnStageKeys,
) {
    companion object {
        fun fromModel(stage: Stage) =
            StageListItem(
                id = stage.id,
                name = stage.name,
                status = stage.status,
                description = stage.description,
                date = stage.date,
                documentUrl = stage.documentUrl,
                videoUrl = stage.videoUrl,
                userDataOnStageKeys = stage.userDataOnStageKeys,
            )
    }
}
