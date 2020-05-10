package com.sherryyuan.buttonup.archives

import com.sherryyuan.buttonup.AppDatabase
import com.sherryyuan.buttonup.archives.repository.EmailsListDao
import com.sherryyuan.buttonup.archives.repository.EmailsRepository
import com.sherryyuan.buttonup.archives.repository.EmailsService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val archivesModule = Kodein.Module("archivesModule") {

    bind<EmailsService>() with singleton {
        instance<Retrofit>("buttondown").create(EmailsService::class.java)
    }

    bind<EmailsListDao>() with provider { instance<AppDatabase>().emailsListDao() }

    bind<EmailsRepository>() with singleton { EmailsRepository() }
}