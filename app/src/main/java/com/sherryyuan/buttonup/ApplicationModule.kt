package com.sherryyuan.buttonup

import com.sherryyuan.buttonup.networking.networkingModule
import com.sherryyuan.buttonup.subscribers.subscribersModule
import org.kodein.di.Kodein

val applicationModule = Kodein.Module("applicationModule") {
    import(networkingModule)
    import(subscribersModule)
}