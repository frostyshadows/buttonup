package com.sherryyuan.buttonup.archives.repository

import com.sherryyuan.buttonup.archives.LocalEmail
import com.sherryyuan.buttonup.archives.EmailsResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmailsService {

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @GET("v1/emails")
    fun getEmails(): Single<EmailsResponse>

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @POST("v1/emails")
    fun saveEmail(@Body email: LocalEmail): Single<Unit>
}