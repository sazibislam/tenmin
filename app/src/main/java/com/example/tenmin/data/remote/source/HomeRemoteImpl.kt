package com.example.tenmin.data.remote.source

import android.util.Log
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.ui.model.Coordinates
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeRemoteImpl(private val client: HttpClient) : HomeRemote {

  override suspend fun getWeatherDataApiCall(coordinate: Coordinates): ResponseResource<WeatherResponse> {

    val apikey = "86c282621580b8120fd7479e434e4114"
    val url =
      "https://api.openweathermap.org/data/3.0/onecall?lat=${coordinate.lat}&lon=${coordinate.lon}&appid=$apikey"


    return try {
      val response = client.get(url) {
      }.body<WeatherResponse>()

      ResponseResource.success(response)
      // when (response.size) {
      //   null -> ResponseResource.error(response)
      //   else -> ResponseResource.success(response)
      // }
    } catch (e: Exception) {
      Log.d("HomeRemoteImpl", "${e.message}")
      ResponseResource.error(WeatherResponse())
    }
  }
}