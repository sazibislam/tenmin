package com.example.tenmin.data.remote.source

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import io.ktor.client.HttpClient
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
}