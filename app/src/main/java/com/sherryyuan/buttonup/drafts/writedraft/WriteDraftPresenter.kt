package com.sherryyuan.buttonup.drafts.writedraft

import com.sherryyuan.buttonup.MainApplication
import com.sherryyuan.buttonup.drafts.LocalDraft
import com.sherryyuan.buttonup.drafts.repository.DraftsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import timber.log.Timber

class WriteDraftPresenter(override val view: WriteDraftContract.View) : WriteDraftContract.Presenter, KodeinAware {

    override val kodein by Kodein.lazy {
        import(MainApplication.appModule)
    }

    private val repository: DraftsRepository by instance()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun start() {
        compositeDisposable = CompositeDisposable()
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun saveDraft(draft: LocalDraft) {
        compositeDisposable.add(
            repository
                .saveDraft(draft)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onDraftSaved()
                }, {
                    Timber.e(it)
                })
        )
    }

}