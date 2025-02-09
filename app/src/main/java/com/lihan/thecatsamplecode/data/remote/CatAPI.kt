package com.lihan.thecatsamplecode.data.remote

import com.lihan.thecatsamplecode.BuildConfig
import com.lihan.thecatsamplecode.data.model.CatDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface CatAPI {

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"
        private const val API_KEY = "live_AZuHUn5WyHtd3kRdlsVy1PRCx9GM0DqozcGaGNVGC7ojSgjeTvqHf94aV6u4ZZDw"
    }

    @Headers(
        "x-api-key: $API_KEY"
    )
    @GET("${BASE_URL}images/search?limit=20")
    suspend fun getCats(): Response<List<CatDto>>
}