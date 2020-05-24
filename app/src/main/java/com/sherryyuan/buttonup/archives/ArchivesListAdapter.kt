package com.sherryyuan.buttonup.archives

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.databinding.ItemEmailBinding
import com.sherryyuan.buttonup.utils.toHumanReadableDateString

class ArchivesListAdapter(private val emails: List<SentEmail>) :
    RecyclerView.Adapter<ArchivesListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding: ItemEmailBinding = ItemEmailBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_email, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
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