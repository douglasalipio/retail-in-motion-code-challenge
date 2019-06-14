package com.douglasalipio.luasforecasts

import com.douglasalipio.luasforecasts.data.FeatureResponse

fun listOfFeature(): MutableList<FeatureResponse> {
    val features = mutableListOf<FeatureResponse>()
    for (i in 1..10) {
        val feature = FeatureResponse("200", "douglas")
        features.add(feature)
    }
    return features
}