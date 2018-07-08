package com.giphy.mvp.api

import com.giphy.mvp.model.SearchModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
    @GET("/v1/gifs/search")
    fun search(@Query("api_key") api_key: String,
               @Query("q") q: String): Observable<SearchModel>
}