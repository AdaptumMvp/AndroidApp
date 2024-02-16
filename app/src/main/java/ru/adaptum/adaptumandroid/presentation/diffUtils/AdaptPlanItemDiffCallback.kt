package ru.adaptum.adaptumandroid.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import ru.adaptum.adaptumandroid.presentation.model.AdaptPlanListItem

class AdaptPlanItemDiffCallback : DiffUtil.ItemCallback<AdaptPlanListItem>() {
    override fun areItemsTheSame(
        oldItem: AdaptPlanListItem,
        newItem: AdaptPlanListItem,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: AdaptPlanListItem,
        newItem: AdaptPlanListItem,
    ) = oldItem == newItem
}
