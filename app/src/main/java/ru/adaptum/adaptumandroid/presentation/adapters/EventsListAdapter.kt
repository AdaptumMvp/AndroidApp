package ru.adaptum.adaptumandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.diffUtils.EventItemDiffCallback
import ru.adaptum.adaptumandroid.presentation.model.EventListItem
import ru.adaptum.adaptumandroid.presentation.viewHolders.EventViewHolder

class EventsListAdapter : ListAdapter<EventListItem, EventViewHolder>(EventItemDiffCallback()) {
    var registerAction: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view, registerAction)
    }

    override fun onBindViewHolder(
        holder: EventViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }
}
