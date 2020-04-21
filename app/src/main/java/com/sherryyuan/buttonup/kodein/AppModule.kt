package com.sherryyuan.buttonup.kodein

import com.sherryyuan.buttonup.drafts.draftsModule
import com.sherryyuan.buttonup.subscribers.subscribersModule
import org.kodein.di.Kodein

var appModule = Kodein.Module("appModule") {
    import(networkingModule)
    import(subscribersModule)
    import(draftsModule)
}