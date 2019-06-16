package com.douglasalipio.luasforecasts.data

import org.simpleframework.xml.*


//data class ForecastsResponse(private val stopInfo: StopInfo)

@Root(name = "stopInfo", strict = false)
data class ForecastsResponse(
    @field:Attribute
    var stop: String = "",
    @field:Attribute
    var created: String = "",
    @field:Element(type = String::class)
    var message: String = "",
    @field:Attribute
    var stopAbv: String = "",
    @field:ElementList(entry = "direction", inline = true, type = Direction::class)
    var directions: MutableList<Direction> = mutableListOf()
)

data class Direction(
    @field:Attribute
    var name: String = "",
    @field:ElementList(entry = "tram", inline = true, type = Tram::class)
    var trams: MutableList<Tram> = mutableListOf()
)

data class Tram(
    @field:Attribute
    var destination: String? = "",
    @field:Attribute
    var dueMins: String? = ""
)

sealed class ForecastsResult {
    object Error : ForecastsResult()
    object Loading : ForecastsResult()
    data class ForecastsData(val forecastResponse: ForecastsResponse) : ForecastsResult()
}