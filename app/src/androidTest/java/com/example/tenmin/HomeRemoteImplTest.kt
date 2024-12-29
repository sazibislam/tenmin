package com.example.tenmin

import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.source.HomeRemoteImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeRemoteImplTest {

  private lateinit var mockClient: HttpClient
  private lateinit var homeRemote: HomeRemoteImpl

  @BeforeTest
  fun setup() {
    // Set up the mock HttpClient
    mockClient = HttpClient(MockEngine) {
      install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
      }
      engine {
        addHandler { request ->
          when (request.url.toString()) {
            "https://jsonplaceholder.typicode.com/photos" -> {
              respond(
                content = """
                                    [
                                        {
                                            "albumId": 1,
                                            "id": 1,
                                            "title": "Sample Title",
                                            "url": "https://via.placeholder.com/600/92c952",
                                            "thumbnailUrl": "https://via.placeholder.com/150/92c952"
                                        },
                                        {
                                            "albumId": 1,
                                            "id": 2,
                                            "title": "Another Title",
                                            "url": "https://via.placeholder.com/600/771796",
                                            "thumbnailUrl": "https://via.placeholder.com/150/771796"
                                        }
                                    ]
                                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
              )
            }

            else -> {
              respondError(HttpStatusCode.NotFound)
            }
          }
        }
      }
    }

    // Instantiate the HomeRemoteImpl with the mock client
    homeRemote = HomeRemoteImpl(mockClient)
  }

  @Test
  fun fetchAlbumDataSuccess() = runBlocking {
    // Act
    val result = homeRemote.fetchAlbumData()

    // Assert
    assertTrue(result is ResponseResource.Success)
    assertEquals(2, (result).data.size)
    assertEquals("Sample Title", result.data[0].title)
    assertEquals("Another Title", result.data[1].title)
  }

  @Test
  fun fetchAlbumDataError() = runBlocking {
    // Arrange
    val failingClient = HttpClient(MockEngine) {
      engine {
        addHandler {
          respondError(HttpStatusCode.InternalServerError)
        }
      }
    }
    val failingHomeRemote = HomeRemoteImpl(failingClient)

    // Act
    val result = failingHomeRemote.fetchAlbumData()

    // Assert
    assertTrue(result is ResponseResource.Error)
    assertEquals(0, (result).errorMessage.size)
  }

  @AfterTest
  fun tearDown() {
    mockClient.close()
  }
}