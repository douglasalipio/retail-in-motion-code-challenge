package com.douglasalipio.luasforecasts.data

import com.douglasalipio.luasforecasts.data.AppDataSource
import com.douglasalipio.luasforecasts.data.remote.RemoteDataSource
import javax.inject.Inject


class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : AppDataSource {

    override fun requestData() = remoteDataSource.requestData()
}