package com.douglasalipio.luasforecasts.data.remote

import com.douglasalipio.luasforecasts.data.AppDataSource

class RemoteDataSource(private val apiHelper: ApiHelper) : AppDataSource {

    override fun requestData(stop : String) = apiHelper.getForecasts(stop)

}