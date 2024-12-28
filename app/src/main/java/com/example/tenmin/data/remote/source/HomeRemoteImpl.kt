package com.example.tenmin.data.remote.source

import android.util.Log
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.ui.model.Coordinates
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class HomeRemoteImpl(private val client: HttpClient) : HomeRemote {

  override suspend fun fetchAlbumData(): ResponseResource<List<AlbumResponse>> {
    val url = "https://jsonplaceholder.typicode.com/photos"

    return try {
      val response = client.get(url) {
      }.bodyAsText()

      val json = Json { ignoreUnknownKeys = true }
      when (val responseObject: List<AlbumResponse> = json.decodeFromString(response)) {
        null -> ResponseResource.error(emptyList())
        else -> ResponseResource.success(responseObject)
      }
    } catch (e: Exception) {
      println("fetchAlbumPhotos: ${e.message}")
      ResponseResource.error(emptyList())
    }
  }

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