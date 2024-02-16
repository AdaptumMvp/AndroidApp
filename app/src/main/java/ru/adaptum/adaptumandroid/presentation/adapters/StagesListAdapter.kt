package ru.adaptum.adaptumandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.diffUtils.TaskItemDiffCallback
import ru.adaptum.adaptumandroid.presentation.model.StageListItem
import ru.adaptum.adaptumandroid.presentation.viewHolders.StageViewHolder

class StagesListAdapter : ListAdapter<StageListItem, StageViewHolder>(TaskItemDiffCallback()) {
    var onClickStage: ((StageListItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): StageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stage, parent, false)
        return StageViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: StageViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            onClickStage?.invoke(currentList[position])
        }
    }
}
