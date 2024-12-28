package com.example.tenmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.tenmin.ui.navigation.NavGraph
import com.example.tenmin.ui.theme.SimpleNavComposeAppTheme

class MainActivity : ComponentActivity() {

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
    NavGraph(navController)
  }
}




