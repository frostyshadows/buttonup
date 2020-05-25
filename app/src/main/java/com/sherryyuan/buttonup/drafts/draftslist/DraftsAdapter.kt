package com.sherryyuan.buttonup.drafts.draftslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.databinding.ItemDraftBinding
import com.sherryyuan.buttonup.drafts.SavedDraft
import com.sherryyuan.buttonup.utils.toHumanReadableDateString

class DraftsAdapter(
    private val drafts: List<SavedDraft>,
    private val navController: NavController
) :
    RecyclerView.Adapter<DraftsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemDraftBinding = ItemDraftBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_draft, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val subject: String = drafts[position].subject
            val body: String = drafts[position].body
            val modificationDate: String = modificationDateText.context.getString(
                R.string.last_modified,
                drafts[position].modificationDate.toHumanReadableDateString()
            )
            root.setOnClickListener {
                navController.navigate(
                    DraftsListFragmentDirections.actionDraftsListFragmentToDraftDetailFragment(
                        subject,
                        body,
                        modificationDate
                    )
                )
            }
            subjectText.text = subject
            bodyText.text = body
            modificationDateText.text = modificationDate
        }
    }

    override fun getItemCount() = drafts.size
}