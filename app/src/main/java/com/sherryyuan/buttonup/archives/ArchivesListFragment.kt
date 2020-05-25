package com.sherryyuan.buttonup.archives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.databinding.FragmentArchivesListBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


class ArchivesListFragment : ArchivesContract.View, Fragment(), KodeinAware {

    override val kodein by closestKodein()

    override lateinit var presenter: ArchivesContract.Presenter

    private var emails: MutableList<SentEmail> = mutableListOf()

    private lateinit var binding: FragmentArchivesListBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onStart() {
        super.onStart()
        presenter = ArchivesPresenter(this)
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivesListBinding.inflate(inflater, container, false)
        return binding.root.also {
            setupNewslettersList(it)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun updateEmails(emails: List<SentEmail>) {
        binding.swipeContainer.isRefreshing = false
        this.emails.clear()
        this.emails.addAll(emails)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupNewslettersList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ArchivesListAdapter(emails)

        binding.swipeContainer.setOnRefreshListener { presenter.refresh() }
        binding.listArchives.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}