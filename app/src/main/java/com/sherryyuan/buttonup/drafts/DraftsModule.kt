package com.sherryyuan.buttonup.drafts

import com.sherryyuan.buttonup.networking.networkingModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val draftsModule = Kodein.Module("draftsModule") {

    import(networkingModule)

    bind<DraftsRepository>() with singleton {
        instance<Retrofit>("buttondown").create(DraftsRepository::class.java)
    }
}