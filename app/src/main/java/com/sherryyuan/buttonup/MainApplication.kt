package com.sherryyuan.buttonup

import android.app.Application
import androidx.room.Room
import com.sherryyuan.buttonup.drafts.draftsModule
import com.sherryyuan.buttonup.kodein.appModule
import com.sherryyuan.buttonup.kodein.networkingModule
import com.sherryyuan.buttonup.subscribers.subscribersModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        appModule = appModule.copy {
            bind<AppDatabase>() with singleton {
                Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "buttonupDatabase"
                ).build()
            }
            import(networkingModule)
            import(subscribersModule)
            import(draftsModule)
        }
        import(appModule)
    }
}