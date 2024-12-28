package com.example.tenmin.domain.repository

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun getAlbumData(): Flow<ResponseResource<List<AlbumResponse>>>
}