package ru.adaptum.adaptumandroid.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T> : DiffUtil.ItemCallback<ListItem<T>>() {
    override fun areItemsTheSame(
        oldItem: ListItem<T>,
        newItem: ListItem<T>,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ListItem<T>,
        newItem: ListItem<T>,
    ): Boolean {
        return oldItem.contentEquals(newItem)
    }
}
