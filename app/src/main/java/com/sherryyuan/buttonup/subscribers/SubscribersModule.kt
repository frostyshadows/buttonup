package com.sherryyuan.buttonup.subscribers

import com.sherryyuan.buttonup.AppDatabase
import com.sherryyuan.buttonup.subscribers.repository.SubscribersDao
import com.sherryyuan.buttonup.subscribers.repository.SubscribersRepository
import com.sherryyuan.buttonup.subscribers.repository.SubscribersService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val subscribersModule = Kodein.Module("subscribersModule") {

    bind<SubscribersService>() with singleton {
        instance<Retrofit>("buttondown").create(SubscribersService::class.java)
    }

    bind<SubscribersDao>() with provider { instance<AppDatabase>().subscribersDao() }

    bind<SubscribersRepository>() with singleton { SubscribersRepository() }
}