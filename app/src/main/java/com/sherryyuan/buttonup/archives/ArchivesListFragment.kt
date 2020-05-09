package com.sherryyuan.buttonup.archives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sherryyuan.buttonup.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


class ArchivesListFragment : ArchivesContract.View, Fragment(), KodeinAware {

    override val kodein by closestKodein()

    override lateinit var presenter: ArchivesContract.Presenter

    private var newsletters: MutableList<SentNewsletter> = mutableListOf()

    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onStart() {
        super.onStart()

        presenter = ArchivesPresenter(this)
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_archives_list, container, false).apply {
            setupNewslettersList(this)
        }
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
    }

    override fun updateNewsletters(newsletters: List<SentNewsletter>) {
        swipeContainer.isRefreshing = false
        this.newsletters.clear()
        this.newsletters.addAll(newsletters)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupNewslettersList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ArchivesListAdapter(newsletters)

        swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container).apply {
            setOnRefreshListener { presenter.refresh() }
        }
        recyclerView = view.findViewById<RecyclerView>(R.id.list_archives).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}