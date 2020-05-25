package com.sherryyuan.buttonup.subscribers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.databinding.FragmentSubscribersBinding

class SubscribersFragment : SubscribersContract.View, Fragment() {

    override lateinit var presenter: SubscribersContract.Presenter

    private var subscribers: MutableList<Subscriber> = mutableListOf()

    private lateinit var binding: FragmentSubscribersBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SubscribersPresenter(this)
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribersBinding.inflate(inflater, container, false)
        return binding.root.also {
            setupSubscribersList(it)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun updateSubscribersList(subscribers: List<Subscriber>) {
        binding.swipeContainer.isRefreshing = false
        this.subscribers.clear()
        this.subscribers.addAll(subscribers)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setupSubscribersList(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = SubscribersAdapter(subscribers)

        binding.swipeContainer.setOnRefreshListener { presenter.refresh() }

        binding.listSubscribers.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}

class SubscribersAdapter(private val subscribers: List<Subscriber>) :
    RecyclerView.Adapter<SubscribersAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subscriber, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = subscribers[position].email
    }

    override fun getItemCount() = subscribers.size
}