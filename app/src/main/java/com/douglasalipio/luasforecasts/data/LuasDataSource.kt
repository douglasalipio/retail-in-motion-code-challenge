package com.douglasalipio.luasforecasts.data

import io.reactivex.Flowable


interface LuasDataSource {

    fun requestData(): Flowable<List<FeatureResponse>>

}