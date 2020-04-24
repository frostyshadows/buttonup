package com.sherryyuan.buttonup.drafts.draftslist

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.drafts.draftslist.DraftsListContract
import com.sherryyuan.buttonup.drafts.repository.DraftsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class DraftsListPresenter(override val view: DraftsListContract.View) : DraftsListContract.Presenter,
    KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val repository: DraftsRepository by instance()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
        fetchDrafts()
    }

    override fun refresh() {
        fetchDrafts(true)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    private fun fetchDrafts(forceRefresh: Boolean = false) {
        compositeDisposable.add(
            repository
                .getDrafts(forceRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.updateDrafts(it)
                }, {
                    it.message
                })
        )
    }
}