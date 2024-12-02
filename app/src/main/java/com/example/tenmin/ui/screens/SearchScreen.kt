package com.example.tenmin.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tenmin.ui.SharedViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
  popBackStack: () -> Unit,
  // viewModel: SharedViewModel = getViewModel()
) {

  // val fullList by viewModel.zilaList.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {

    val fullList = listOf("Dhaka", "Chittagong", "Khulna", "Sylhet", "Barisal", "Rangpur")
    var query by remember { mutableStateOf("") }
    val filteredList = fullList.filter { it.contains(query, ignoreCase = true) }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      // Search TextField
      TextField(
        value = query,
        onValueChange = { query = it },
        label = { Text("Search Zila") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Display filtered results in a horizontal row
      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
      ) {
        items(filteredList.size) { zila ->

          Card(
            modifier = Modifier
              .padding(8.dp)
              .size(100.dp, 50.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f)
          ) {
            Box(contentAlignment = Alignment.Center) {

              Text(
                text = filteredList[zila],
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary
              )
            }
          }
        }
      }
    }
  }
}

// @Preview(showBackground = true)
// @Composable
// private fun DefaultPreview() {
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