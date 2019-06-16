package com.douglasalipio.luasforecasts.data

import org.simpleframework.xml.Root


@Root(name = "stopInfo", strict = false)
data class ForecastsResponse(
    var stop: String,
    var created: String,
    var message: String,
    var stopAbv: String,
    var direction: List<Direction>
)

data class Tram(
    var destination: String,
    var dueMins: String
)

data class Direction(
    var name: String,
    var tram: Tram
)

sealed class ForecastsResult {
    object Error : ForecastsResult()
    object Loading : ForecastsResult()
    data class ForecastsData(val data: List<ForecastsResponse>) : ForecastsResult()
}