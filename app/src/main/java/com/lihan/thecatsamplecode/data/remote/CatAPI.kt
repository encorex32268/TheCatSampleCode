package com.lihan.thecatsamplecode.data.remote

import com.lihan.thecatsamplecode.BuildConfig
import com.lihan.thecatsamplecode.data.model.CatDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface CatAPI {

    @Headers(
        "x-api-key: ${BuildConfig.API_KEY}"
    )
    @GET("images/search?limit=20")
    suspend fun getCats(): Response<List<CatDto>>
}