package com.sherryyuan.buttonup.drafts

import com.sherryyuan.buttonup.AppDatabase
import com.sherryyuan.buttonup.drafts.repository.DraftsListDao
import com.sherryyuan.buttonup.drafts.repository.DraftsRepository
import com.sherryyuan.buttonup.drafts.repository.DraftsService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val draftsModule = Kodein.Module("draftsModule") {

    bind<DraftsService>() with singleton {
        instance<Retrofit>("buttondown").create(DraftsService::class.java)
    }

    bind<DraftsListDao>() with provider { instance<AppDatabase>().draftsListDao() }

    bind<DraftsRepository>() with singleton { DraftsRepository() }
}