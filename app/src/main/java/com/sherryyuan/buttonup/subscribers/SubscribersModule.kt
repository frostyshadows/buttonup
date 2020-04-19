package com.sherryyuan.buttonup.subscribers

import com.sherryyuan.buttonup.networking.networkingModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val subscribersModule = Kodein.Module("subscribersModule") {

    import(networkingModule)

    bind<SubscribersRepository>() with singleton {
        instance<Retrofit>("buttondown").create(SubscribersRepository::class.java)
    }
}