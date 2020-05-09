package com.sherryyuan.buttonup.archives.repository

import com.sherryyuan.buttonup.archives.LocalNewsletter
import com.sherryyuan.buttonup.archives.NewslettersResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewslettersService {

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @GET("v1/newsletters")
    fun getNewsletters(): Single<NewslettersResponse>

    @Headers("Authorization: Token 714e10e2-f0b5-4c5d-9bf2-a463417e104a")
    @POST("v1/newsletters")
    fun saveNewsletter(@Body newsletter: LocalNewsletter): Single<Unit>
}