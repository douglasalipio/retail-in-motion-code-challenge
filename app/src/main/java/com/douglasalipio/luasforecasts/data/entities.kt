package com.douglasalipio.luasforecasts.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeatureResponse(
    @Expose
    @SerializedName("name")
    private var statusCode: String,

    @Expose
    @SerializedName("surname")
    private var message: String
)

sealed class FeatureResult {
    object Error : FeatureResult()
    object Loading : FeatureResult()
    data class FeatureData(val data: List<FeatureResponse>) : FeatureResult()
}