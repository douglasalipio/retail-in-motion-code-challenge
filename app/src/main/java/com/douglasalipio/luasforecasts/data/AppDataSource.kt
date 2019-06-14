package com.douglasalipio.luasforecasts.data

import io.reactivex.Flowable


interface AppDataSource {

    fun requestData(): Flowable<List<FeatureResponse>>

}