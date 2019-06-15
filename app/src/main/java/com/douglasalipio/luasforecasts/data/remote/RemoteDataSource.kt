package com.douglasalipio.luasforecasts.data.remote

import com.douglasalipio.luasforecasts.data.LuasDataSource

class RemoteDataSource(private val apiHelper: ApiHelper) : LuasDataSource {

    override fun requestData() = apiHelper.getData()

}