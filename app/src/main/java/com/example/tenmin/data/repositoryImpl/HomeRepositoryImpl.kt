package com.example.tenmin.data.repositoryImpl

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.DummyResponse
import com.example.tenmin.data.remote.source.HomeRemote
import com.example.tenmin.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
  private val remote: HomeRemote,
) : HomeRepository {

  override suspend fun getHomeData(): Flow<ResponseResource<DummyResponse>> =
    flow {
      val responseResource = when (val response = remote.getHomeList()) {
        is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
        is ResponseResource.Success -> ResponseResource.success(response.data)
      }
      emit(responseResource)
    }
}