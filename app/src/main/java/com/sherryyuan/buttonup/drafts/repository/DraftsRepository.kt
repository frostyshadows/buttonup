package com.sherryyuan.buttonup.drafts.repository

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.drafts.Draft
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class DraftsRepository : KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val service: DraftsService by instance()
    private val roomDao: DraftsDao by instance()

    fun getDrafts(forceRefresh: Boolean = false): Single<List<Draft>> =
        if (forceRefresh) {
            getDraftsFromNetwork()
        } else {
            roomDao.getAll().filter { it.isNotEmpty() }.switchIfEmpty(getDraftsFromNetwork())
        }

    private fun getDraftsFromNetwork(): Single<List<Draft>> =
        service.getDrafts().map { response ->
            response.results.also {
                roomDao.insertAll(it)
            }
        }

}