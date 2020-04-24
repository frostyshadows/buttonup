package com.sherryyuan.buttonup.drafts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class DraftsFragment : DraftsContract.View, Fragment(), KodeinAware {

    override val kodein by closestKodein()

    override lateinit var presenter: DraftsContract.Presenter

    private var drafts: MutableList<Draft> = mutableListOf()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = DraftsPresenter(this)
        presenter.start()
        fetchDrafts()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drafts, container, false).apply {
            setupDraftsList(this)
        }
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
    }

    override fun updateDrafts(drafts: List<Draft>) {
        this.drafts.clear()
        this.drafts.addAll(drafts)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupDraftsList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = DraftsAdapter(drafts)

        recyclerView = view.findViewById<RecyclerView>(R.id.list_drafts).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun fetchDrafts() {
        presenter.fetchDrafts()
    }
}

class DraftsAdapter(private val drafts: List<Draft>) :
    RecyclerView.Adapter<DraftsAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_draft, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = drafts[position].subject
    }

    override fun getItemCount() = drafts.size
}