package com.douglasalipio.luasforecasts.data.remote

import com.douglasalipio.luasforecasts.data.ForecastsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = " https://luasforecasts.rpa.ie"

interface ApiHelper {

    //http://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=ran&encrypt=false
    //https://luasforecasts.rpa.ie/get.ashx?action&stop=ran&encrypt=false
    //https://luasforecasts.rpa.ie/xml/get.ashx?action&stop=ran&encrypt=false
    @GET("/xml/get.ashx?action=forecast")
    fun getForecasts(
        @Query("stop") stop: String,
        @Query("encrypt") encrypt: Boolean = false
    ): Flowable<ForecastsResponse>

}