package ru.adaptum.adaptumandroid.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.adaptum.adaptumandroid.domain.entity.Stage
import java.text.SimpleDateFormat
import java.util.Locale

data class StageDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("updated_at") val date: String,
    @SerializedName("file") val documentUrl: String?,
    @SerializedName("link_video") val videoUrl: String?,
    @SerializedName("user_data_on_stage_keys") val userDataOnStageKeys: UserDataOnStageKeysDto,
    @SerializedName("user_data_on_stage") val userDataOnStage: UserDataOnStageDto,
) {
    fun toModel() =
        Stage(
            id = id,
            name = title,
            status = userDataOnStage.status,
            description = description,
            date = parseDate(date),
            documentUrl = documentUrl ?: "",
            videoUrl = videoUrl ?: "",
            userDataOnStageKeys = userDataOnStageKeys.toModel(),
        )

    private fun parseDate(dateDto: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return outputFormat.format(inputFormat.parse(dateDto)!!)
    }
}
