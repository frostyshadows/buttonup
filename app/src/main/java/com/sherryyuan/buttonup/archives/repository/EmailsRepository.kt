package com.sherryyuan.buttonup.archives.repository

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.archives.LocalEmail
import com.sherryyuan.buttonup.archives.SentEmail
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class EmailsRepository : KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val service: EmailsService by instance()
    private val roomDao: EmailsListDao by instance()

    fun getEmails(forceRefresh: Boolean = false): Single<List<SentEmail>> =
        if (forceRefresh) {
            getEmailsFromNetwork()
        } else {
            roomDao.getAll().filter { it.isNotEmpty() }.switchIfEmpty(getEmailsFromNetwork())
        }

    fun saveEmail(email: LocalEmail): Single<Unit> =
        service.saveEmail(email)

    private fun getEmailsFromNetwork(): Single<List<SentEmail>> =
        service.getEmails().map { response ->
            response.results.also {
                roomDao.insertAll(it)
            }
        }

}