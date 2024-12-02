package com.example.tenmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.tenmin.ui.SharedViewModel
import com.example.tenmin.ui.navigation.NavGraph
import com.example.tenmin.ui.theme.SimpleNavComposeAppTheme

class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<SharedViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainScreen()
      InitData()
    }

  }

  @Composable
  fun InitData() {
    val zilaList by viewModel.zilaList.collectAsState()
    println(zilaList)
  }
}

@Composable
private fun MainScreen() {
  SimpleNavComposeAppTheme {
    val navController = rememberNavController()
    NavGraph(navController)
  }
}


