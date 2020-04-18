package com.sherryyuan.buttonup.subscribers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SubscribersPresenter(override val view: SubscribersContract.View) : SubscribersContract.Presenter {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun fetchSubscribers() {
        compositeDisposable.add(
            subscribersService
                .getSubscribers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.updateSubscribersList(it.results)
                }
        )
    }

}