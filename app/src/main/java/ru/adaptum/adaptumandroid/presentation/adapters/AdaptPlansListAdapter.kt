package ru.adaptum.adaptumandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.domain.entity.MentorInfo
import ru.adaptum.adaptumandroid.presentation.diffUtils.DiffCallback
import ru.adaptum.adaptumandroid.presentation.diffUtils.ListItem
import ru.adaptum.adaptumandroid.presentation.model.AdaptPlanListItem
import ru.adaptum.adaptumandroid.presentation.viewHolders.AdaptPlanViewHolder

class AdaptPlansListAdapter : ListAdapter<ListItem<AdaptPlanListItem>, AdaptPlanViewHolder>(
    DiffCallback(),
) {
    var onClickAction: ((Int) -> Unit)? = null
    var onClickSendMessageAction: ((MentorInfo) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AdaptPlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapt_plan, parent, false)
        return AdaptPlanViewHolder(view, onClickAction, onClickSendMessageAction)
    }

    override fun onBindViewHolder(
        holder: AdaptPlanViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position].content)
    }
}
