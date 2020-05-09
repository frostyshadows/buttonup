package com.sherryyuan.buttonup.archives

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.utils.toHumanReadableDateString

class ArchivesListAdapter(private val drafts: List<SentNewsletter>) :
    RecyclerView.Adapter<ArchivesListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.name_text)
        val descriptionText: TextView = view.findViewById(R.id.description_text)
        val creationDateText: TextView = view.findViewById(R.id.creation_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_newsletter, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            nameText.text = drafts[position].name
            descriptionText.text = drafts[position].description
            creationDateText.text = creationDateText.context.getString(
                R.string.created,
                drafts[position].creationDate.toHumanReadableDateString()
            )
        }
    }

    override fun getItemCount() = drafts.size
}