package com.example.tenmin.data.remote.source

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.ui.model.Coordinates

interface HomeRemote {
  suspend fun fetchAlbumData(): ResponseResource<List<AlbumResponse>>

  suspend fun getWeatherDataApiCall(coordinate: Coordinates): ResponseResource<WeatherResponse>
}