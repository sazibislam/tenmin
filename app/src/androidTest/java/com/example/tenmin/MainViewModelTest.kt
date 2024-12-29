package com.example.tenmin

import app.cash.turbine.test
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.domain.repository.HomeRepository
import com.example.tenmin.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

  private lateinit var viewModel: MainViewModel
  private val repository: HomeRepository = mock()
  private val testScheduler = TestCoroutineScheduler()
  private val testDispatcher = StandardTestDispatcher(testScheduler)
  private val testScope = TestScope(testDispatcher)

  @Before
  fun setup() {
    viewModel = MainViewModel(repository)
  }

  @Test
  fun getAlbumData_emits_success_state() = testScope.runTest {
    // Arrange
    val mockAlbumList = listOf(
      AlbumResponse(albumId = 1, id = 1, title = "Album 1", thumbnailUrl = "url1", url = ""),
      AlbumResponse(albumId = 2, id = 2, title = "Album 2", thumbnailUrl = "url2", url = "")
    )

    whenever(repository.getAlbumData()).thenReturn(
      flowOf(ResponseResource.Success(mockAlbumList))
    )

    // Act
    viewModel = MainViewModel(repository)
    advanceUntilIdle()

    // Assert
    viewModel.albumState.test {
      assertEquals(mockAlbumList, awaitItem())
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun getAlbumData_handles_error_state() = testScope.runTest {
    // Arrange
    // val errorMessage = "Some error occurred"
    whenever(repository.getAlbumData()).thenReturn(
      flowOf(ResponseResource.Error(emptyList()))
    )

    // Act
    viewModel = MainViewModel(repository)
    advanceUntilIdle()

    // Assert
    viewModel.albumState.test {
      assertEquals(null, awaitItem()) // Initial value remains null
      cancelAndIgnoreRemainingEvents()
    }
  }
}
