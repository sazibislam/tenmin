package com.example.tenmin.data.repositoryImpl

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.data.remote.source.HomeRemote
import com.example.tenmin.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
  private val remote: HomeRemote,
) : HomeRepository {
  override suspend fun getAlbumData(): Flow<ResponseResource<List<AlbumResponse>>> = flow {
    val responseResource = when (val response = remote.fetchAlbumData()) {
      is ResponseResource.Success -> ResponseResource.success(getFirstAlbumById(response.data))
      is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
    }
    emit(responseResource)
  }

  private fun getFirstAlbumById(albumList: List<AlbumResponse>): List<AlbumResponse> =
    albumList.groupBy { album -> album.albumId }.map { (_, albums) -> albums.first() }
}