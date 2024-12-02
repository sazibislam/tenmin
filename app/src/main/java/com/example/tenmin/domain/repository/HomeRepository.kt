package com.example.tenmin.domain.repository

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.DummyResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun getHomeData(): Flow<ResponseResource<DummyResponse>>
}