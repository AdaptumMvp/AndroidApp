package ru.adaptum.adaptumandroid.presentation.viewHolders

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.model.StageListItem

class StageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(stage: StageListItem) {
        view.findViewById<TextView>(R.id.task_name_tv).text = stage.name
        view.findViewById<TextView>(R.id.description_tv).text = Html.fromHtml(stage.description, Html.FROM_HTML_MODE_COMPACT)
        view.findViewById<TextView>(R.id.created_date_tv).text = stage.date
        view.findViewById<TextView>(R.id.status_tv).text = stage.status
    }
}
