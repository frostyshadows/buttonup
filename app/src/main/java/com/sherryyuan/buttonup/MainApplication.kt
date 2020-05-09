package com.sherryyuan.buttonup

import android.app.Application
import androidx.room.Room
import com.sherryyuan.buttonup.archives.archivesModule
import com.sherryyuan.buttonup.drafts.draftsModule
import com.sherryyuan.buttonup.kodein.networkingModule
import com.sherryyuan.buttonup.subscribers.subscribersModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import timber.log.Timber


class MainApplication : Application(), KodeinAware {

    companion object {
        lateinit var appModule: Kodein.Module
    }

    override val kodein by Kodein.lazy {
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        appModule = Kodein.Module("appModule") {
            bind<AppDatabase>() with singleton {
                Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "buttonupDatabase"
                ).build()
            }
            import(networkingModule)
            import(subscribersModule)
            import(draftsModule)
            import(archivesModule)
        }
    }
}