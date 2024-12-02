package com.example.tenmin.data.repositoryImpl

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.data.remote.source.HomeRemote
import com.example.tenmin.domain.repository.HomeRepository
import com.example.tenmin.ui.model.Coordinates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
  private val remote: HomeRemote,
) : HomeRepository {

  override suspend fun getWeatherData(coordinate: Coordinates): Flow<ResponseResource<WeatherResponse>> =
    flow {
      val responseResource = when (val response = remote.getWeatherDataApiCall(coordinate)) {
        is ResponseResource.Success -> ResponseResource.success(response.data)
        is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
      }
      emit(responseResource)
    }
}