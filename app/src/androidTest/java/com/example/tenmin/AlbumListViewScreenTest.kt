package com.example.tenmin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.ui.MainViewModel
import com.example.tenmin.ui.screens.AlbumListViewScreen
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AlbumListViewScreenTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private val mockViewModel: MainViewModel = mock()

  private val fakeAlbums = listOf(
    AlbumResponse(albumId = 1, id = 1, title = "Album 1", thumbnailUrl = ""),
    AlbumResponse(albumId = 2, id = 2, title = "Album 2", thumbnailUrl = "")
  )

  @Test
  fun testLoadingIndicatorIsDisplayed() {
    val loadingState = MutableStateFlow<List<AlbumResponse>?>(null)
    whenever(mockViewModel.albumState).thenReturn(loadingState)

    composeTestRule.setContent {
      AlbumListViewScreen(
        popBackStack = {},
        viewmodel = mockViewModel
      )
    }

    // Assert that the loading spinner is displayed
    composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
  }

  @Test
  fun testAlbumListIsDisplayed() {
    val albumState = MutableStateFlow(fakeAlbums)
    whenever(mockViewModel.albumState).thenReturn(albumState)

    composeTestRule.setContent {
      AlbumListViewScreen(
        popBackStack = {},
        viewmodel = mockViewModel
      )
    }

    // Assert that each album is displayed
    fakeAlbums.forEach { album ->
      composeTestRule.onNodeWithText("Title: ${album.title}").assertIsDisplayed()
      composeTestRule.onNodeWithText("Album Id: ${album.albumId}").assertIsDisplayed()
      composeTestRule.onNodeWithText("Id: ${album.id}").assertIsDisplayed()
    }
  }

  @Test
  fun testAlbumRowClickTriggersPopBackStack() {
    val albumState = MutableStateFlow(fakeAlbums)
    whenever(mockViewModel.albumState).thenReturn(albumState)

    var isPopBackStackCalled = false
    composeTestRule.setContent {
      AlbumListViewScreen(
        popBackStack = { isPopBackStackCalled = true },
        viewmodel = mockViewModel
      )
    }

    // Perform a click on the first album row
    composeTestRule.onNodeWithText("Title: Album 1").performClick()

    // Assert that the popBackStack function was called
    assert(isPopBackStackCalled)
  }
}