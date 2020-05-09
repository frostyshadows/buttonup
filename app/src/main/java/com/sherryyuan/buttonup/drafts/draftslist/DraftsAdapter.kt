package com.sherryyuan.buttonup.drafts.draftslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.drafts.SavedDraft
import com.sherryyuan.buttonup.utils.toHumanReadableDateString

class DraftsAdapter(private val drafts: List<SavedDraft>) :
    RecyclerView.Adapter<DraftsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val subjectText: TextView = view.findViewById(R.id.subject_text)
        val bodyText: TextView = view.findViewById(R.id.body_text)
        val modificationDateText: TextView = view.findViewById(R.id.modification_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_draft, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            subjectText.text = drafts[position].subject
            bodyText.text = drafts[position].body
            modificationDateText.text = modificationDateText.context.getString(
                R.string.last_modified,
                drafts[position].modificationDate.toHumanReadableDateString()
            )
        }
    }

    override fun getItemCount() = drafts.size
}