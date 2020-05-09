package com.sherryyuan.buttonup.archives

import com.sherryyuan.buttonup.AppDatabase
import com.sherryyuan.buttonup.archives.repository.NewslettersListDao
import com.sherryyuan.buttonup.archives.repository.NewslettersRepository
import com.sherryyuan.buttonup.archives.repository.NewslettersService
import com.sherryyuan.buttonup.drafts.repository.DraftsService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val archivesModule = Kodein.Module("archivesModule") {

    bind<NewslettersService>() with singleton {
        instance<Retrofit>("buttondown").create(NewslettersService::class.java)
    }

    bind<NewslettersListDao>() with provider { instance<AppDatabase>().newsLettersListDao() }

    bind<NewslettersRepository>() with singleton { NewslettersRepository() }
}