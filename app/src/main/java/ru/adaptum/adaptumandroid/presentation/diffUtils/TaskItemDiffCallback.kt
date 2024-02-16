package ru.adaptum.adaptumandroid.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import ru.adaptum.adaptumandroid.presentation.model.StageListItem

class TaskItemDiffCallback : DiffUtil.ItemCallback<StageListItem>() {
    override fun areItemsTheSame(
        oldItem: StageListItem,
        newItem: StageListItem,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: StageListItem,
        newItem: StageListItem,
    ) = oldItem == newItem
}
