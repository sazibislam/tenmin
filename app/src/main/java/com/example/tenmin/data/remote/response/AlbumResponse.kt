package com.example.tenmin.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(
  @SerialName("albumId") var albumId: Int? = null,
  @SerialName("id") var id: Int? = null,
  @SerialName("thumbnailUrl") var thumbnailUrl: String? = null,
  @SerialName("title") var title: String = "",
  @SerialName("url") var url: String? = ""
)