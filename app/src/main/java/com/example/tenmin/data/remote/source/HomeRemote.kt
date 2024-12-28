package com.example.tenmin.data.remote.source

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse

interface HomeRemote {
  suspend fun fetchAlbumData(): ResponseResource<List<AlbumResponse>>
}