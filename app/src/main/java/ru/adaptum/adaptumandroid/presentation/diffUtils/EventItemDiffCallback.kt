package ru.adaptum.adaptumandroid.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import ru.adaptum.adaptumandroid.presentation.model.EventListItem

class EventItemDiffCallback : DiffUtil.ItemCallback<EventListItem>() {
    override fun areItemsTheSame(
        oldItem: EventListItem,
        newItem: EventListItem,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: EventListItem,
        newItem: EventListItem,
    ) = oldItem == newItem
}
