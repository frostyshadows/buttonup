package com.sherryyuan.buttonup.subscribers

import com.sherryyuan.buttonup.MainApplication
import com.sherryyuan.buttonup.subscribers.repository.SubscribersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SubscribersPresenter(override val view: SubscribersContract.View) :
    SubscribersContract.Presenter, KodeinAware {

    override val kodein by Kodein.lazy {
        import(MainApplication.appModule)
    }

    private val repository: SubscribersRepository by instance()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
        fetchSubscribers()
    }

    override fun refresh() {
        fetchSubscribers(true)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    private fun fetchSubscribers(forceRefresh: Boolean = false) {
        compositeDisposable.add(
            repository
                .getSubscribers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.updateSubscribersList(it)
                }, {
                    // No op
                })
        )
    }
}