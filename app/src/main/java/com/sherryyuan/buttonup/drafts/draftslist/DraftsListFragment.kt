package com.sherryyuan.buttonup.drafts.draftslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.drafts.SavedDraft
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


class DraftsListFragment : DraftsListContract.View, Fragment(), KodeinAware {

    override val kodein by closestKodein()

    override lateinit var presenter: DraftsListContract.Presenter

    private var drafts: MutableList<SavedDraft> = mutableListOf()

    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onStart() {
        super.onStart()

        presenter = DraftsListPresenter(this)
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drafts_list, container, false).apply {
            setupDraftsList(this)
        }
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
    }

    override fun updateDrafts(drafts: List<SavedDraft>) {
        swipeContainer.isRefreshing = false
        this.drafts.clear()
        this.drafts.addAll(drafts)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupDraftsList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = DraftsAdapter(drafts)

        swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container).apply {
            setOnRefreshListener { presenter.refresh() }
        }
        recyclerView = view.findViewById<RecyclerView>(R.id.list_drafts).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}

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
        holder.subjectText.text = drafts[position].subject
        holder.bodyText.text = drafts[position].body
        holder.modificationDateText.text = drafts[position].modificationDate
    }

    override fun getItemCount() = drafts.size
}