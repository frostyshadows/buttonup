package com.sherryyuan.buttonup.subscribers

import com.sherryyuan.buttonup.networking.buttondownRetrofit
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface SubscribersService {

    @Headers("Authorization: Token 0917577d-e13f-4c03-98d3-4be897c4fdb1")
    @GET("v1/subscribers")
    fun getSubscribers(): Observable<SubscribersListResponse>
}

val subscribersService: SubscribersService = buttondownRetrofit.create(SubscribersService::class.java)