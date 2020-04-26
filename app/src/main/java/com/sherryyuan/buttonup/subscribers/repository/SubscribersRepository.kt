package com.sherryyuan.buttonup.subscribers.repository

import com.sherryyuan.buttonup.MainApplication.Companion.appModule
import com.sherryyuan.buttonup.subscribers.Subscriber
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SubscribersRepository : KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    private val service: SubscribersService by instance()
    private val roomDao: SubscribersDao by instance()

    fun getSubscribers(forceRefresh: Boolean = false): Single<List<Subscriber>> =
        if (forceRefresh) {
            getSubscribersFromNetwork()
        } else {
            roomDao.getAll().filter { it.isNotEmpty() }.switchIfEmpty(getSubscribersFromNetwork())
        }

    private fun getSubscribersFromNetwork(): Single<List<Subscriber>> =
        service.getSubscribers().map { response ->
            response.results.also {
                roomDao.insertAll(it)
            }
        }

}