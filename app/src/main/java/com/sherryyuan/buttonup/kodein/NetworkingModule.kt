package com.sherryyuan.buttonup.kodein

import com.google.gson.GsonBuilder
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = Kodein.Module("networkingModule") {
    bind<Retrofit>(tag = "buttondown") with singleton {
        Retrofit.Builder()
            .baseUrl("https://api.buttondown.email/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create()
                )
            )
            .build()
    }
}