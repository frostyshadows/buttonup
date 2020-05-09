package com.sherryyuan.buttonup.archives.repository

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.archives.LocalNewsletter
import com.sherryyuan.buttonup.archives.SentNewsletter
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class NewslettersRepository : KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val service: NewslettersService by instance()
    private val roomDao: NewslettersListDao by instance()

    fun getNewsletters(forceRefresh: Boolean = false): Single<List<SentNewsletter>> =
        if (forceRefresh) {
            getNewslettersFromNetwork()
        } else {
            roomDao.getAll().filter { it.isNotEmpty() }.switchIfEmpty(getNewslettersFromNetwork())
        }

    fun saveNewsletter(newsletter: LocalNewsletter): Single<Unit> =
        service.saveNewsletter(newsletter)

    private fun getNewslettersFromNetwork(): Single<List<SentNewsletter>> =
        service.getNewsletters().map { response ->
            response.results.also {
                roomDao.insertAll(it)
            }
        }

}