package com.sherryyuan.buttonup.subscribers

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SubscribersPresenter(override val view: SubscribersContract.View, context: Context) :
    SubscribersContract.Presenter, KodeinAware {

    override val kodein by closestKodein(context)

    private val repository: SubscribersRepository by instance()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun fetchSubscribers() {
        compositeDisposable.add(
            repository
                .getSubscribers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.updateSubscribersList(it.results)
                }
        )
    }
}