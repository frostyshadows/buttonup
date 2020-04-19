package com.sherryyuan.buttonup.subscribers

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface SubscribersRepository {

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @GET("v1/subscribers")
    fun getSubscribers(): Observable<SubscribersListResponse>
}