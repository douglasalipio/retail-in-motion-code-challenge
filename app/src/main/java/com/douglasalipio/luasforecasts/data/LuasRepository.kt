package com.douglasalipio.luasforecasts.data

import com.douglasalipio.luasforecasts.data.remote.RemoteDataSource
import javax.inject.Inject


class LuasRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : LuasDataSource {

    override fun requestData() = remoteDataSource.requestData()
}