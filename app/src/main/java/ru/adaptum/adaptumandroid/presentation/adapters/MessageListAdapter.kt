package ru.adaptum.adaptumandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.model.MessageListItem

class MessageListAdapter : RecyclerView.Adapter<MessageListAdapter.MessageItemViewHolder>() {
    var messageListItemList = listOf<MessageListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MessageItemViewHolder {
        val layout =
            when (viewType) {
                VIEW_TYPE_SENT -> R.layout.item_message_sent
                VIEW_TYPE_RECEIVED -> R.layout.item_message_received
                else -> throw RuntimeException("Unknown view type: $viewType")
            }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MessageItemViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: MessageItemViewHolder,
        position: Int,
    ) {
        val message = messageListItemList[position]
        viewHolder.view.setOnLongClickListener {
            true
        }
        viewHolder.tvMessage.text = message.message
    }

    override fun onViewRecycled(viewHolder: MessageItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvMessage.text = ""
        viewHolder.tvMessage.setTextColor(
            ContextCompat.getColor(
                viewHolder.view.context,
                android.R.color.white,
            ),
        )
    }

    override fun getItemCount(): Int {
        return messageListItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = messageListItemList[position]
        return if (item.isSent) {
            VIEW_TYPE_SENT
        } else {
            VIEW_TYPE_RECEIVED
        }
    }

    class MessageItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvMessage = view.findViewById<TextView>(R.id.message_tv)
    }

    companion object {
        const val VIEW_TYPE_RECEIVED = 100
        const val VIEW_TYPE_SENT = 101
        const val MENTOR_ID = "MENTOR_ID"
    }
}
