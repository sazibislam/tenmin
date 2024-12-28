package com.example.tenmin.domain.repository

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.ui.model.Coordinates
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun getAlbumData(): Flow<ResponseResource<List<AlbumResponse>>>
  suspend fun getWeatherData(coordinate: Coordinates): Flow<ResponseResource<WeatherResponse>>
}