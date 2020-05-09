package com.sherryyuan.buttonup.archives

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.archives.repository.NewslettersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import timber.log.Timber

class ArchivesPresenter(override val view: ArchivesContract.View) : ArchivesContract.Presenter,
    KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val repository: NewslettersRepository by instance()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
        fetchNewsletters()
    }

    override fun refresh() {
        fetchNewsletters(true)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    private fun fetchNewsletters(forceRefresh: Boolean = false) {
        compositeDisposable.add(
            repository
                .getNewsletters(forceRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsletters ->
                    view.updateNewsletters(newsletters.sortedByDescending { it.creationDate })
                }, {
                    Timber.e(it)
                })
        )
    }
}