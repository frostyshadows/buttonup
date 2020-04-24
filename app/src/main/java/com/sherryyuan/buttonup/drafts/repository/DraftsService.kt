package com.sherryyuan.buttonup.drafts.repository

import com.sherryyuan.buttonup.drafts.LocalDraft
import com.sherryyuan.buttonup.drafts.DraftsListResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DraftsService {

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @GET("v1/drafts")
    fun getDrafts(): Single<DraftsListResponse>

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @POST("v1/drafts")
    fun saveDraft(@Body draft: LocalDraft): Single<Unit>
}