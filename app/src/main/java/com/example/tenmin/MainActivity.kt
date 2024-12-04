package com.example.tenmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.tenmin.ui.SearchViewModel
import com.example.tenmin.ui.SharedViewModel
import com.example.tenmin.ui.navigation.NavGraph
import com.example.tenmin.ui.theme.SimpleNavComposeAppTheme

class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<SearchViewModel>()
  private val sharedViewModel = SharedViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SimpleNavComposeAppTheme {
        MainScreen()
      }
    }
  }

  @Composable
  private fun MainScreen() {
    val navController = rememberNavController()
    val zilaList by viewModel.zilaList.collectAsState()
    sharedViewModel.zilaList = zilaList
    NavGraph(navController, sharedViewModel)
  }
}




