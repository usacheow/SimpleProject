package com.kapmayn.data.api

import com.kapmayn.data.models.Stub
import io.reactivex.Single
import retrofit2.http.GET

interface StubApi {

    @GET("stubs")
    fun getStubs(): Single<Stub>
}