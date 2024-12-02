package com.example.tenmin.data.remote.source

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.DummyResponse

interface HomeRemote {
  suspend fun getHomeList(): ResponseResource<DummyResponse>
}