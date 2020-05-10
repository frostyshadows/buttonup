package com.sherryyuan.buttonup.archives

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.utils.toHumanReadableDateString

class ArchivesListAdapter(private val emails: List<SentEmail>) :
    RecyclerView.Adapter<ArchivesListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val subjectText: TextView = view.findViewById(R.id.subject_text)
        val bodyText: TextView = view.findViewById(R.id.body_text)
        val publishedDateText: TextView = view.findViewById(R.id.published_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_email, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            subjectText.text = emails[position].subject
            bodyText.text = emails[position].body
            publishedDateText.text = publishedDateText.context.getString(
                R.string.published,
                emails[position].creationDate.toHumanReadableDateString()
            )
        }
    }

    override fun getItemCount() = emails.size
}