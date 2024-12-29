package com.example.tenmin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.ui.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable fun AlbumListViewScreen(
  popBackStack: () -> Unit,
  viewmodel: MainViewModel = koinViewModel()
) {

  viewmodel.getAlbumData()

  var loading by remember { mutableStateOf(true) }
  val albums by viewmodel.albumState.collectAsState()

  LaunchedEffect(albums) {
    loading = albums == null
  }

  Column(modifier = Modifier.fillMaxSize()) {

    TopBar()

    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center // Center the loading indicator
    ) {
      if (loading) {
        // Show loading spinner
        CircularProgressIndicator(
          modifier = Modifier.testTag("loadingIndicator")
        )
      } else {

        Column(modifier = Modifier.fillMaxSize()) {

          // List of items
          LazyColumn(
            modifier = Modifier
              .fillMaxSize()
              .padding(16.dp)
          ) {
            albums?.let { albumList ->
              items(albumList) { album ->
                AlbumRow(album, popBackStack)
              }
            }
          }
        }
      }
    }
  }
}

@Composable fun TopBar() {
  TopAppBar(
    title = { Text(text = "Samsung App") },
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = MaterialTheme.colors.onPrimary,
    elevation = 4.dp
  )
}

@Composable fun AlbumRow(album: AlbumResponse, popBackStack: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { popBackStack() } // Makes the row clickable
      .padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberAsyncImagePainter(
        model = album.thumbnailUrl,
        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery), // Placeholder while loading
        error = painterResource(id = android.R.drawable.ic_menu_gallery)       // Default thumbnail if loading fails
      ),
      contentDescription = "Album Image",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(64.dp)
        .padding(end = 16.dp)
    )

    // Text details
    Column {
      Text(text = "Title: ${album.title}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
      Text(text = "Album Id: ${album.albumId}", fontSize = 14.sp, color = Color.Gray)
      Text(text = "Id: ${album.id}", fontSize = 14.sp, color = Color.Gray)
    }
  }
}

// @Preview(showBackground = true)
// @Composable
// popBackStack: () -> Unit,
//   viewmodel: MainViewModel = koinViewModel()
// ) {
//   SimpleNavComposeAppTheme(useSystemUiController = false) {
//     Surface(
//       modifier = Modifier.fillMaxSize(),
//       color = MaterialTheme.colors.background
//     ) {
//       SearchScreen(
//         popBackStack = {}
//       )
//     }
//   }
// }