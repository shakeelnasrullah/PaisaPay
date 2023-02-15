package com.pak.paisapay.data.remote


import com.pak.paisapay.BuildConfig
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("currency_data/live")
    suspend fun getCurrencyRates(
        @Query("source") searchQuery: String = "USD",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): ResponseBody

}