package com.example.tenmin.data.remote.source

import android.util.Log
import com.example.tenmin.data.ApiRoutes.ENDPOINT_PRODUCTS
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.DummyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeRemoteImpl(private val client: HttpClient) : HomeRemote {

  override suspend fun getHomeList(): ResponseResource<DummyResponse> {

    return try {
      val response = client.get(ENDPOINT_PRODUCTS) {
        // header("Authorization", "Bearer $token")
      }.body<DummyResponse>()

      ResponseResource.success(response)
      // when (response.size) {
      //   null -> ResponseResource.error(response)
      //   else -> ResponseResource.success(response)
      // }
    } catch (e: Exception) {
      Log.d("HomeRemoteImpl", "${e.message}")
      ResponseResource.error(DummyResponse())
    }
  }
}