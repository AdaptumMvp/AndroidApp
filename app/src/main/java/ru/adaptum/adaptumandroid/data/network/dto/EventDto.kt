package ru.adaptum.adaptumandroid.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.adaptum.adaptumandroid.domain.entity.Event

data class EventDto(
    @SerializedName("id") val id: Int,
    @SerializedName("cover") val photoUrl: String,
    @SerializedName("status_txt") val status: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("start_date") val date: String,
) {
    fun toModel() = Event(id, photoUrl, status, title, description, date)
}
