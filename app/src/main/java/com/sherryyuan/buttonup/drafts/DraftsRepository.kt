package com.sherryyuan.buttonup.drafts

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface DraftsRepository {

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @GET("v1/drafts")
    fun getDrafts(): Observable<DraftsListResponse>
}