package com.example.tenmin.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
  @SerialName("data") var data: List<WeatherData>? = null,
  @SerialName("cod") var cod: Int? = null,
  @SerialName("message") var message: String? = null,
  @SerialName("lat") var lat: Double? = null,
  @SerialName("lon") var lon: Double? = null,
  @SerialName("timezone") var timezone: String? = null,
  @SerialName("timezone_offset") var timezone_offset: Int? = null
)

@Serializable
data class WeatherData(
  @SerialName("clouds") var clouds: Int? = null,
  @SerialName("dew_point") var dew_point: Double? = null,
  @SerialName("dt") var dt: Int? = null,
  @SerialName("feels_like") var feels_like: Double? = null,
  @SerialName("humidity") var humidity: Int? = null,
  @SerialName("pressure") var pressure: Int? = null,
  @SerialName("sunrise") var sunrise: Int? = null,
  @SerialName("sunset") var sunset: Int? = null,
  @SerialName("temp") var temp: Double? = null,
  @SerialName("uvi") var uvi: Double? = null,
  @SerialName("visibility") var visibility: Int? = null,
  @SerialName("weather") var weather: List<Weather>? = null,
  @SerialName("wind_deg") var wind_deg: Int? = null,
  @SerialName("wind_speed") var wind_speed: Double? = null
)

@Serializable
data class Weather(
  @SerialName("description") var description: String? = null,
  @SerialName("icon") var icon: String? = null,
  @SerialName("id") var id: Int? = null,
  @SerialName("main") var main: String? = null
)