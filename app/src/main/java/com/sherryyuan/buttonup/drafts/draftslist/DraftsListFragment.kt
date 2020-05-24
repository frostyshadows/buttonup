package com.sherryyuan.buttonup.drafts.draftslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.databinding.FragmentDraftsListBinding
import com.sherryyuan.buttonup.drafts.SavedDraft
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


class DraftsListFragment : DraftsListContract.View, Fragment(), KodeinAware {

    override val kodein by closestKodein()

    override lateinit var presenter: DraftsListContract.Presenter

    private var drafts: MutableList<SavedDraft> = mutableListOf()

    private lateinit var binding: FragmentDraftsListBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onStart() {
        super.onStart()

        presenter = DraftsListPresenter(this)
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDraftsListBinding.inflate(inflater, container, false)
        return binding.root.also {
            setupDraftsList(it)
        }
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
    }

    override fun updateDrafts(drafts: List<SavedDraft>) {
        binding.swipeContainer.isRefreshing = false
        this.drafts.clear()
        this.drafts.addAll(drafts)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupDraftsList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = DraftsAdapter(drafts)

        binding.swipeContainer.setOnRefreshListener { presenter.refresh() }
        binding.listDrafts.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}