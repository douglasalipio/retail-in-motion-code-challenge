package com.douglasalipio.luasforecasts.data

import io.reactivex.Flowable


interface LuasDataSource {

    fun requestForecasts(stop: String): Flowable<ForecastsResponse>

}