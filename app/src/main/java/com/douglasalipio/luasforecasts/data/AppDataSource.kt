package com.douglasalipio.luasforecasts.data

import com.douglasalipio.luasforecasts.data.ForecastsResponse
import io.reactivex.Flowable


interface AppDataSource {

    fun requestData(stop : String): Flowable<ForecastsResponse>
}